package net.st915.minesweeper.logic.application

import net.st915.minesweeper.GameState

object DoNothing {

  def apply[F[_]: DoNothing]: DoNothing[F] = implicitly

}

trait DoNothing[F[_]] {

  def perform(implicit gameState: GameState): F[GameState]

}
