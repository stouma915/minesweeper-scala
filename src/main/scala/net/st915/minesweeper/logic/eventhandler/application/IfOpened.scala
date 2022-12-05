package net.st915.minesweeper.logic.eventhandler.application

import net.st915.minesweeper.{Coordinate, GameState}

object IfOpened {

  def apply[F[_]: IfOpened]: IfOpened[F] = implicitly

}

trait IfOpened[F[_]] {

  def perform(coord: Coordinate)(ifOpened: => F[GameState])(ifNotOpened: => F[GameState])(implicit
  gameState: GameState): F[GameState]

}
