package net.st915.minesweeper.logic.impl

import cats.effect.Sync
import net.st915.minesweeper.Consts.ElementID
import net.st915.minesweeper.GameState
import net.st915.minesweeper.event.ButtonClickEvent
import net.st915.minesweeper.logic.application.HandleButtonClickEvent

class SyncHandleButtonClickEvent[F[_]: Sync] extends HandleButtonClickEvent[F] {

  override def handle(event: ButtonClickEvent)(implicit gameState: GameState): F[GameState] =
    event.buttonId match
      case ElementID.ToggleFlagModeButtonId => onToggleFlagPlaceModeClicked
      case ElementID.RestartButtonId        => onRestartClicked
      case _                                => Sync[F].pure(gameState)

  def onToggleFlagPlaceModeClicked(implicit gameState: GameState): F[GameState] =
    Sync[F].pure {
      gameState.copy(inFlagPlaceMode = !gameState.inFlagPlaceMode)
    }

  def onRestartClicked(implicit gameState: GameState): F[GameState] =
    Sync[F].pure(GameState.empty)

}
