package net.st915.minesweeper.logic.eventhandler.application

import net.st915.minesweeper.{Coordinate, GameState}

object IfFlagged {

  def apply[F[_]: IfFlagged]: IfFlagged[F] = implicitly

}

trait IfFlagged[F[_]] {

  def perform(coord: Coordinate)(ifFlagged: => F[GameState])(ifNotFlagged: => F[GameState])(implicit
  gameState: GameState): F[GameState]

}
