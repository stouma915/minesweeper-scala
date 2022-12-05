package net.st915.minesweeper.logic

import cats.effect.Sync
import net.st915.minesweeper.{Coordinate, GameState}
import net.st915.minesweeper.logic.application.*
import net.st915.minesweeper.logic.impl.*

object OpenCellLogic {

  def wired[F[_]: Sync](coord: Coordinate)(implicit gameState: GameState): F[GameState] = {
    implicit val _ifFlagged: IfFlagged[F] = ApplicativeIfFlagged[F]
    implicit val _ifOpened: IfOpened[F] = ApplicativeIfOpened[F]

    implicit val _doNothing: DoNothing[F] = ApplicativeDoNothing[F]
    implicit val _addOpenedCoord: AddOpenedCoord[F] = ApplicativeAddOpenedCoord[F]
    implicit val _ifNotOpenedAndNotFlagged: IfNotOpenedAndNotFlagged[F] =
      ApplicativeIfNotOpenedAndNotFlagged[F]

    OpenCellLogic(coord)
  }

  def apply[
    F[_]: Sync: IfNotOpenedAndNotFlagged: AddOpenedCoord: DoNothing
  ](coord: Coordinate)(implicit gameState: GameState): F[GameState] =
    IfNotOpenedAndNotFlagged[F].perform(coord) {
      AddOpenedCoord[F].add(coord)
    } {
      DoNothing[F].perform
    }

}
