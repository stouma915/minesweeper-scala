package net.st915.minesweeper.logic.instances

import cats.Monad
import net.st915.minesweeper.{Coordinate, GameState}
import net.st915.minesweeper.logic.instances.*
import net.st915.minesweeper.logic.typeclasses.*

class MonadIfOpened[F[_]: Monad] extends IfOpened[F] {

  import cats.syntax.flatMap.*

  override def perform(coord: Coordinate)(ifTrue: => F[GameState])(ifFalse: => F[GameState])(using
  GameState): F[GameState] = {
    given IsOpened[F] = MonadIsOpened[F]

    IsOpened[F].check(coord) >>= (if (_) ifTrue else ifFalse)
  }

}
