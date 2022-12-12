package net.st915.minesweeper.logic.typeclasses

import net.st915.minesweeper.GameState

object IfInFlagPlaceMode {

  def apply[F[_]](using ev: IfInFlagPlaceMode[F]): IfInFlagPlaceMode[F] = ev

}

trait IfInFlagPlaceMode[F[_]] {

  def perform(ifTrue: => F[GameState])(ifFalse: => F[GameState])(using GameState): F[GameState]

}
