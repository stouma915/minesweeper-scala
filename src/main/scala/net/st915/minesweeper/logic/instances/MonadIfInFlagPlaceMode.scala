package net.st915.minesweeper.logic.instances

import cats.Monad
import net.st915.minesweeper.GameState
import net.st915.minesweeper.logic.instances.*
import net.st915.minesweeper.logic.typeclasses.*
import net.st915.minesweeper.util.HigherKindIf

class MonadIfInFlagPlaceMode[F[_]: Monad] extends IfInFlagPlaceMode[F] {

  override def perform(using GameState): HigherKindIf[F, GameState] = {
    given IsInFlagPlaceMode[F] = MonadIsInFlagPlaceMode[F]

    HigherKindIf()(IsInFlagPlaceMode[F].check)
  }

}
