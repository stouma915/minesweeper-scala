package net.st915.minesweeper.logic.instances

import cats.Monad
import net.st915.minesweeper.GameState
import net.st915.minesweeper.logic.instances.*
import net.st915.minesweeper.logic.typeclasses.*

class MonadIfInFlagPlaceMode[F[_]: Monad] extends IfInFlagPlaceMode[F] {

  import cats.syntax.flatMap.*

  override def perform(ifTrue: => F[GameState])(ifFalse: => F[GameState])(using
  GameState): F[GameState] = {
    given IsInFlagPlaceMode[F] = MonadIsInFlagPlaceMode[F]

    IsInFlagPlaceMode[F].check >>= (if (_) ifTrue else ifFalse)
  }

}
