package net.st915.minesweeper.eventlistener.tasks

import cats.effect.Sync
import net.st915.minesweeper.GameState
import net.st915.minesweeper.event.CellClickEvent
import net.st915.minesweeper.logic.gamelogics.OpenCell

object CellClickEventListener {

  def wired[F[_]: Sync](event: CellClickEvent)(using GameState): F[GameState] =
    OpenCell.wired[F](event.coord)

}
