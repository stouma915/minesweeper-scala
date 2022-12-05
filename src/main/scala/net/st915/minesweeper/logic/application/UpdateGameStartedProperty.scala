package net.st915.minesweeper.logic.application

import net.st915.minesweeper.GameState

object UpdateGameStartedProperty {

  def apply[F[_]: UpdateGameStartedProperty]: UpdateGameStartedProperty[F] = implicitly

}

trait UpdateGameStartedProperty[F[_]] {

  def update(implicit gameState: GameState): F[GameState]

}
