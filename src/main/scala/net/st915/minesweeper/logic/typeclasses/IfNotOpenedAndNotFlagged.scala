package net.st915.minesweeper.logic.typeclasses

import net.st915.minesweeper.util.HigherKindIf
import net.st915.minesweeper.{Coordinate, GameState}

object IfNotOpenedAndNotFlagged {

  def apply[F[_]](using ev: IfNotOpenedAndNotFlagged[F]): IfNotOpenedAndNotFlagged[F] = ev

}

trait IfNotOpenedAndNotFlagged[F[_]] {

  def perform(coord: Coordinate)(using GameState): HigherKindIf[F, GameState]

}
