package net.st915.minesweeper.logic.typeclasses

import net.st915.minesweeper.{Coordinate, GameState}
import net.st915.util.HigherKindIf

object IfNotOpened {

  def apply[F[_]](using ev: IfNotOpened[F]): IfNotOpened[F] = ev

}

trait IfNotOpened[F[_]] {

  def perform(coord: Coordinate)(using GameState): HigherKindIf[F, GameState]

}
