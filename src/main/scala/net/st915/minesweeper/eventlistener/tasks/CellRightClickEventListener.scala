package net.st915.minesweeper.eventlistener.tasks

import cats.effect.Sync
import net.st915.minesweeper.GameState
import net.st915.minesweeper.event.CellRightClickEvent
import net.st915.minesweeper.logic.gamelogics.ToggleFlagged

object CellRightClickEventListener {

  def wired[F[_]: Sync](event: CellRightClickEvent)(using GameState): F[GameState] =
    ToggleFlagged.wired[F](event.coord)

}
