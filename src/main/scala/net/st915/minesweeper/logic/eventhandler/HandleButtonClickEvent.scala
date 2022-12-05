package net.st915.minesweeper.logic.eventhandler

import cats.effect.Sync
import net.st915.minesweeper.Consts.ElementID
import net.st915.minesweeper.GameState
import net.st915.minesweeper.event.ButtonClickEvent
import net.st915.minesweeper.logic.*
import net.st915.minesweeper.logic.application.DoNothing
import net.st915.minesweeper.logic.impl.ApplicativeDoNothing

object HandleButtonClickEvent {

  def wired[F[_]: Sync](event: ButtonClickEvent)(implicit gameState: GameState): F[GameState] =
    implicit val _doNothing: DoNothing[F] = ApplicativeDoNothing[F]

    HandleButtonClickEvent(event)

  def apply[F[_]: Sync: DoNothing](event: ButtonClickEvent)(implicit
  gameState: GameState): F[GameState] =
    event.buttonId match
      case ElementID.ToggleFlagModeButtonId =>
        ToggleFlagPlaceModeLogic.wired[F]
      case ElementID.RestartButtonId =>
        RestartLogic.wired[F]
      case _ =>
        DoNothing[F].perform

}
