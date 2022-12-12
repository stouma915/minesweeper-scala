package net.st915.minesweeper.logic.gamelogics.typeclasses

import net.st915.minesweeper.GameState

object CanExitFlagPlaceMode {

  def apply[F[_]](using ev: CanExitFlagPlaceMode[F]): CanExitFlagPlaceMode[F] = ev

}

trait CanExitFlagPlaceMode[F[_]] {

  def perform(using GameState): F[GameState]

}
