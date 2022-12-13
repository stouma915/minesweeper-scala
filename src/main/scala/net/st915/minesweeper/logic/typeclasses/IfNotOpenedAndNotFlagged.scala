package net.st915.minesweeper.logic.typeclasses

import net.st915.minesweeper.{Coordinate, GameState}
import net.st915.util.HigherKindIf

object IfNotOpenedAndNotFlagged {

  def apply[F[_]](using ev: IfNotOpenedAndNotFlagged[F]): IfNotOpenedAndNotFlagged[F] = ev

}

trait IfNotOpenedAndNotFlagged[F[_]] {

  def perform(coord: Coordinate)(using GameState): HigherKindIf[F, GameState]

}
