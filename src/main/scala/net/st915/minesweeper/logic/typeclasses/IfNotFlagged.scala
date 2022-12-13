package net.st915.minesweeper.logic.typeclasses

import net.st915.minesweeper.{Coordinate, GameState}
import net.st915.util.HigherKindIf

object IfNotFlagged {

  def apply[F[_]](using ev: IfNotFlagged[F]): IfNotFlagged[F] = ev

}

trait IfNotFlagged[F[_]] {

  def perform(coord: Coordinate)(using GameState): HigherKindIf[F, GameState]

}
