package net.st915.minesweeper.logic.eventhandler

import cats.effect.Sync
import net.st915.minesweeper.GameState
import net.st915.minesweeper.event.CellClickEvent
import net.st915.minesweeper.logic.eventhandler.application.*
import net.st915.minesweeper.logic.eventhandler.impl.*

object HandleCellClickEvent {

  def wired[F[_]: Sync](event: CellClickEvent)(implicit gameState: GameState): F[GameState] = {
    implicit val _doNothing: DoNothing[F] = ApplicativeDoNothing[F]
    implicit val _addOpenedCoord: AddOpenedCoord[F] = ApplicativeAddOpenedCoord[F]
    implicit val _addOpenedCoordIfNotExists: AddOpenedCoordIfNotExists[F] =
      ApplicativeAddOpenedCoordIfNotExists[F]

    HandleCellClickEvent(event)
  }

  def apply[
    F[_]: Sync: AddOpenedCoordIfNotExists
  ](event: CellClickEvent)(implicit gameState: GameState): F[GameState] =
    AddOpenedCoordIfNotExists[F].add(event.coord)
}
