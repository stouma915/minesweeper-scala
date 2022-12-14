package net.st915.minesweeper.logic.typeclasses

import net.st915.minesweeper.GameState
import net.st915.util.HigherKindIf

object IfInFlagPlaceMode {

  def apply[F[_]](using ev: IfInFlagPlaceMode[F]): IfInFlagPlaceMode[F] = ev

}

trait IfInFlagPlaceMode[F[_]] {

  def perform(using GameState): HigherKindIf[F, GameState]

}
