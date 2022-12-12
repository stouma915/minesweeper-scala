package net.st915.minesweeper.eventlistener.tasks.typeclasses

import net.st915.minesweeper.GameState

object CanResetGameState {

  def apply[F[_]](using ev: CanResetGameState[F]): CanResetGameState[F] = ev

}

trait CanResetGameState[F[_]] {

  def perform(using GameState): F[GameState]

}
