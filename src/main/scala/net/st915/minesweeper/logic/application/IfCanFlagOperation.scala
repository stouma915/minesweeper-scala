package net.st915.minesweeper.logic.application

import net.st915.minesweeper.{Coordinate, GameState}

object IfCanFlagOperation {

  def apply[F[_]: IfCanFlagOperation]: IfCanFlagOperation[F] = implicitly

}

trait IfCanFlagOperation[F[_]] {

  def perform(coord: Coordinate)(program: => F[GameState])(implicit
  gameState: GameState): F[GameState]

}
