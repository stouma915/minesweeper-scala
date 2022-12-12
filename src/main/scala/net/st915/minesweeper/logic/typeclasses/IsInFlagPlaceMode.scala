package net.st915.minesweeper.logic.typeclasses

import net.st915.minesweeper.GameState

object IsInFlagPlaceMode {

  def apply[F[_]](using ev: IsInFlagPlaceMode[F]): IsInFlagPlaceMode[F] = ev

}

trait IsInFlagPlaceMode[F[_]] {

  def check(using GameState): F[Boolean]

}
