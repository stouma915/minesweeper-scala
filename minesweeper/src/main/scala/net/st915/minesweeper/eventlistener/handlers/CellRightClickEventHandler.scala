package net.st915.minesweeper.eventlistener.handlers

import cats.effect.Sync
import net.st915.minesweeper.GameState
import net.st915.minesweeper.event.CellRightClickEvent
import net.st915.minesweeper.logic.gamelogics.ToggleFlagged

object CellRightClickEventHandler {

  def wired[F[_]: Sync](event: CellRightClickEvent)(using GameState): F[GameState] =
    ToggleFlagged.wired[F](event.coord)

}
