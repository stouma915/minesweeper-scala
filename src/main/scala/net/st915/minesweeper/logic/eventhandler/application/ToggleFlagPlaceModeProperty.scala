package net.st915.minesweeper.logic.eventhandler.application

import net.st915.minesweeper.GameState

object ToggleFlagPlaceModeProperty {

  def apply[F[_]: ToggleFlagPlaceModeProperty]: ToggleFlagPlaceModeProperty[F] = implicitly

}

trait ToggleFlagPlaceModeProperty[F[_]] {

  def update(implicit gameState: GameState): F[GameState]

}
