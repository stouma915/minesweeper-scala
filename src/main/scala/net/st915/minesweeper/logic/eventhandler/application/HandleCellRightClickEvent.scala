package net.st915.minesweeper.logic.eventhandler.application

import net.st915.minesweeper.GameState
import net.st915.minesweeper.event.CellRightClickEvent

object HandleCellRightClickEvent {

  def apply[F[_]: HandleCellRightClickEvent]: HandleCellRightClickEvent[F] = implicitly

}

trait HandleCellRightClickEvent[F[_]] {

  def handle(event: CellRightClickEvent)(implicit gameState: GameState): F[GameState]

}
