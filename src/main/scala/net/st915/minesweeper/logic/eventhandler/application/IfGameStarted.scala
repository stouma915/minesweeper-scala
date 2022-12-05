package net.st915.minesweeper.logic.eventhandler.application

import net.st915.minesweeper.GameState

object IfGameStarted {

  def apply[F[_]: IfGameStarted]: IfGameStarted[F] = implicitly

}

trait IfGameStarted[F[_]] {

  def perform(ifStarted: => F[GameState])(ifNotStarted: => F[GameState])(implicit
  gameState: GameState): F[GameState]

}
