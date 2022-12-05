package net.st915.minesweeper.logic.eventhandler

import cats.effect.Sync
import net.st915.minesweeper.GameState
import net.st915.minesweeper.event.CellRightClickEvent
import net.st915.minesweeper.logic.PlaceFlagLogic

object HandleCellRightClickEvent {

  def wired[F[_]: Sync](event: CellRightClickEvent)(implicit gameState: GameState): F[GameState] =
    HandleCellRightClickEvent(event)

  def apply[F[_]: Sync](event: CellRightClickEvent)(implicit gameState: GameState): F[GameState] =
    PlaceFlagLogic.wired[F](event.coord)

}
