package net.st915.minesweeper.logic.typeclasses

import net.st915.minesweeper.util.HigherKindIf
import net.st915.minesweeper.{Coordinate, GameState}

object IfOpened {

  def apply[F[_]](using ev: IfOpened[F]): IfOpened[F] = ev

}

trait IfOpened[F[_]] {

  def perform(coord: Coordinate)(using GameState): HigherKindIf[F, GameState]

}
