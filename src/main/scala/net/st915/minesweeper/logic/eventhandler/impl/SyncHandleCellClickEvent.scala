package net.st915.minesweeper.logic.eventhandler.impl

import cats.effect.Sync
import net.st915.minesweeper.GameState
import net.st915.minesweeper.event.CellClickEvent
import net.st915.minesweeper.logic.eventhandler.application.HandleCellClickEvent

class SyncHandleCellClickEvent[F[_]: Sync] extends HandleCellClickEvent[F] {

  override def handle(event: CellClickEvent)(implicit gameState: GameState): F[GameState] =
    Sync[F].pure {
      println(s"clicked: ${event.coord}")
      gameState
    }

}
