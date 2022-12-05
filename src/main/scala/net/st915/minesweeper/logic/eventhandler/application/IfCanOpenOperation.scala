package net.st915.minesweeper.logic.eventhandler.application

import net.st915.minesweeper.GameState

object IfCanOpenOperation {

  def apply[F[_]: IfCanOpenOperation]: IfCanOpenOperation[F] = implicitly

}

trait IfCanOpenOperation[F[_]] {

  def perform(ifCan: => F[GameState])(ifCannot: => F[GameState])(implicit
  gameState: GameState): F[GameState]

}
