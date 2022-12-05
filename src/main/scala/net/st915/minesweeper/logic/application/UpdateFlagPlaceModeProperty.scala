package net.st915.minesweeper.logic.application

import net.st915.minesweeper.GameState

object UpdateFlagPlaceModeProperty {

  def apply[F[_]: UpdateFlagPlaceModeProperty]: UpdateFlagPlaceModeProperty[F] = implicitly

}

trait UpdateFlagPlaceModeProperty[F[_]] {

  def update(newProperty: Boolean)(implicit gameState: GameState): F[GameState]

}
