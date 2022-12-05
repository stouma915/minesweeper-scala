package net.st915.minesweeper.logic.eventhandler.application

import net.st915.minesweeper.{Coordinate, GameState}

object IfNotOpenedAndNotFlagged {

  def apply[F[_]: IfNotOpenedAndNotFlagged]: IfNotOpenedAndNotFlagged[F] = implicitly

}

trait IfNotOpenedAndNotFlagged[F[_]] {

  def perform(coord: Coordinate)(program: => F[GameState])(implicit
  gameState: GameState): F[GameState]

}
