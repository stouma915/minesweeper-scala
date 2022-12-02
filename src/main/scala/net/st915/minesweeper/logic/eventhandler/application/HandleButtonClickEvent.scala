package net.st915.minesweeper.logic.eventhandler.application

import net.st915.minesweeper.GameState
import net.st915.minesweeper.event.ButtonClickEvent

object HandleButtonClickEvent {

  def apply[F[_]: HandleButtonClickEvent]: HandleButtonClickEvent[F] = implicitly

}

trait HandleButtonClickEvent[F[_]] {

  def handle(event: ButtonClickEvent)(implicit gameState: GameState): F[GameState]

}
