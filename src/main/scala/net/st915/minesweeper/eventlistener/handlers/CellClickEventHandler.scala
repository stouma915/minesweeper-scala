package net.st915.minesweeper.eventlistener.handlers

import cats.effect.Sync
import net.st915.minesweeper.GameState
import net.st915.minesweeper.event.CellClickEvent
import net.st915.minesweeper.logic.gamelogics.OpenCell

object CellClickEventHandler {

  def wired[F[_]: Sync](event: CellClickEvent)(using GameState): F[GameState] =
    OpenCell.wired[F](event.coord)

}
