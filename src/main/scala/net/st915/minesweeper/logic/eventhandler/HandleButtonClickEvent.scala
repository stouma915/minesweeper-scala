package net.st915.minesweeper.logic.eventhandler

import cats.effect.Sync
import net.st915.minesweeper.Consts.ElementID
import net.st915.minesweeper.GameState
import net.st915.minesweeper.event.ButtonClickEvent

object HandleButtonClickEvent {

  def wired[F[_]: Sync](event: ButtonClickEvent)(implicit gameState: GameState): F[GameState] =
    HandleButtonClickEvent(event)

  def apply[F[_]: Sync](event: ButtonClickEvent)(implicit gameState: GameState): F[GameState] =
    event.buttonId match
      case ElementID.ToggleFlagModeButtonId => onToggleFlagPlaceModeClicked
      case ElementID.RestartButtonId        => onRestartClicked
      case _                                => Sync[F].pure(gameState)

  def onToggleFlagPlaceModeClicked[F[_]: Sync](implicit gameState: GameState): F[GameState] =
    Sync[F].pure {
      gameState.copy(inFlagPlaceMode = !gameState.inFlagPlaceMode)
    }

  def onRestartClicked[F[_]: Sync](implicit gameState: GameState): F[GameState] =
    Sync[F].pure(GameState.empty)

}
