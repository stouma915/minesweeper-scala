package net.st915.minesweeper.logic.eventhandler.impl

import cats.effect.Sync
import net.st915.minesweeper.GameState
import net.st915.minesweeper.event.CellRightClickEvent
import net.st915.minesweeper.logic.eventhandler.application.HandleCellRightClickEvent

class SyncHandleCellRightClickEvent[F[_]: Sync] extends HandleCellRightClickEvent[F] {

  override def handle(event: CellRightClickEvent)(implicit gameState: GameState): F[GameState] =
    Sync[F].pure {
      println(s"right clicked: ${event.coord}")
      gameState
    }

}
