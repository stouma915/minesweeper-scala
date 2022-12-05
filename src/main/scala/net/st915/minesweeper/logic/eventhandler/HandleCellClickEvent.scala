package net.st915.minesweeper.logic.eventhandler

import cats.effect.Sync
import net.st915.minesweeper.GameState
import net.st915.minesweeper.event.CellClickEvent
import net.st915.minesweeper.logic.OpenCellLogic

object HandleCellClickEvent {

  def wired[F[_]: Sync](event: CellClickEvent)(implicit gameState: GameState): F[GameState] =
    HandleCellClickEvent(event)

  def apply[F[_]: Sync](event: CellClickEvent)(implicit gameState: GameState): F[GameState] =
    OpenCellLogic.wired[F](event.coord)
}
