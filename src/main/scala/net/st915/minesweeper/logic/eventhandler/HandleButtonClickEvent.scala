package net.st915.minesweeper.logic.eventhandler

import cats.effect.Sync
import net.st915.minesweeper.Consts.ElementID
import net.st915.minesweeper.GameState
import net.st915.minesweeper.event.ButtonClickEvent
import net.st915.minesweeper.logic.eventhandler.application.*
import net.st915.minesweeper.logic.eventhandler.impl.*

object HandleButtonClickEvent {

  def wired[F[_]: Sync](event: ButtonClickEvent)(implicit gameState: GameState): F[GameState] = {
    implicit val _doNothing: DoNothing[F] = ApplicativeDoNothing[F]
    implicit val _resetGameState: ResetGameState[F] = ApplicativeResetGameState[F]
    implicit val _updateFlagPlaceModeProperty: UpdateFlagPlaceModeProperty[F] =
      ApplicativeUpdateFlagPlaceModeProperty[F]
    implicit val _toggleFlagPlaceModeProperty: ToggleFlagPlaceModeProperty[F] =
      ApplicativeToggleFlagPlaceModeProperty[F]

    HandleButtonClickEvent(event)
  }

  def apply[
    F[_]: Sync: DoNothing: ResetGameState: ToggleFlagPlaceModeProperty
  ](event: ButtonClickEvent)(implicit gameState: GameState): F[GameState] =
    event.buttonId match
      case ElementID.ToggleFlagModeButtonId => onToggleFlagPlaceModeClicked
      case ElementID.RestartButtonId        => onRestartClicked
      case _                                => DoNothing[F].perform

  def onToggleFlagPlaceModeClicked[
    F[_]: Sync: ToggleFlagPlaceModeProperty
  ](implicit gameState: GameState): F[GameState] =
    ToggleFlagPlaceModeProperty[F].update

  def onRestartClicked[
    F[_]: Sync: ResetGameState
  ](implicit gameState: GameState): F[GameState] =
    ResetGameState[F].perform

}
