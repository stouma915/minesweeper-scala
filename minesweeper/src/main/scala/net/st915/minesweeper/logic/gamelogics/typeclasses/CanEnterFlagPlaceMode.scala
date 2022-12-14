package net.st915.minesweeper.logic.gamelogics.typeclasses

import net.st915.minesweeper.GameState

object CanEnterFlagPlaceMode {

  def apply[F[_]](using ev: CanEnterFlagPlaceMode[F]): CanEnterFlagPlaceMode[F] = ev

}

trait CanEnterFlagPlaceMode[F[_]] {

  def perform(using GameState): F[GameState]

}
