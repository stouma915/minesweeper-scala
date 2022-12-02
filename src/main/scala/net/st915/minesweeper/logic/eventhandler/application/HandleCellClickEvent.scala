package net.st915.minesweeper.logic.eventhandler.application

import net.st915.minesweeper.GameState
import net.st915.minesweeper.event.CellClickEvent

object HandleCellClickEvent {

  def apply[F[_]: HandleCellClickEvent]: HandleCellClickEvent[F] = implicitly

}

trait HandleCellClickEvent[F[_]] {

  def handle(event: CellClickEvent)(implicit gameState: GameState): F[GameState]

}
