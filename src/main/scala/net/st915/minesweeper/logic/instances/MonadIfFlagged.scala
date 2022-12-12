package net.st915.minesweeper.logic.instances

import cats.Monad
import net.st915.minesweeper.logic.instances.*
import net.st915.minesweeper.logic.typeclasses.*
import net.st915.minesweeper.{Coordinate, GameState}

class MonadIfFlagged[F[_]: Monad] extends IfFlagged[F] {

  import cats.syntax.flatMap.*

  override def perform(coord: Coordinate)(ifTrue: => F[GameState])(ifFalse: => F[GameState])(using
  GameState): F[GameState] = {
    given IsFlagged[F] = MonadIsFlagged[F]

    IsFlagged[F].check(coord) >>= (if (_) ifTrue else ifFalse)
  }

}
