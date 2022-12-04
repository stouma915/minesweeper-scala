package net.st915.minesweeper.logic.eventhandler

import cats.effect.Sync
import net.st915.minesweeper.GameState
import net.st915.minesweeper.event.CellRightClickEvent

object HandleCellRightClickEvent {

  def wired[F[_]: Sync](event: CellRightClickEvent)(implicit gameState: GameState): F[GameState] =
    HandleCellRightClickEvent(event)

  def apply[F[_]: Sync](event: CellRightClickEvent)(implicit gameState: GameState): F[GameState] =
    Sync[F].pure {
      println(s"right clicked: ${event.coord}")
      gameState
    }

}
