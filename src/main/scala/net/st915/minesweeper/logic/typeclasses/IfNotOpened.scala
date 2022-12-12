package net.st915.minesweeper.logic.typeclasses

import net.st915.minesweeper.{Coordinate, GameState}

object IfNotOpened {

  def apply[F[_]](using ev: IfNotOpened[F]): IfNotOpened[F] = ev

}

trait IfNotOpened[F[_]] {

  def perform(coord: Coordinate)(ifTrue: => F[GameState])(ifFalse: => F[GameState])(using
  GameState): F[GameState]

}
