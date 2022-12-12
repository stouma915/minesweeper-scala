package net.st915.minesweeper.logic.typeclasses

import net.st915.minesweeper.{Coordinate, GameState}

object IfFlagged {

  def apply[F[_]](using ev: IfFlagged[F]): IfFlagged[F] = ev

}

trait IfFlagged[F[_]] {

  def perform(coord: Coordinate)(ifTrue: => F[GameState])(ifFalse: => F[GameState])(using
  GameState): F[GameState]

}
