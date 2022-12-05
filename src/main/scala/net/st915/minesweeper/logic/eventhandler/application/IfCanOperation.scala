package net.st915.minesweeper.logic.eventhandler.application

import net.st915.minesweeper.GameState

object IfCanOperation {

  def apply[F[_]: IfCanOperation]: IfCanOperation[F] = implicitly

}

trait IfCanOperation[F[_]] {

  def perform(ifCan: => F[GameState])(ifCannot: => F[GameState])(implicit
  gameState: GameState): F[GameState]

}
