package net.st915.minesweeper.logic.application

import net.st915.minesweeper.GameState

object IfInFlagPlaceMode {

  def apply[F[_]: IfInFlagPlaceMode]: IfInFlagPlaceMode[F] = implicitly

}

trait IfInFlagPlaceMode[F[_]] {

  def perform[A](ifIn: => F[A])(ifNotIn: => F[A])(implicit gameState: GameState): F[A]

}
