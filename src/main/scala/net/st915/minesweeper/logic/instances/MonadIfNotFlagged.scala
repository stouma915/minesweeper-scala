package net.st915.minesweeper.logic.instances

import cats.Monad
import net.st915.minesweeper.{Coordinate, GameState}
import net.st915.minesweeper.logic.instances.*
import net.st915.minesweeper.logic.typeclasses.*

class MonadIfNotFlagged[F[_]: Monad] extends IfNotFlagged[F] {

  import net.st915.minesweeper.syntax.ifSyntax.*

  override def perform(coord: Coordinate)(ifTrue: => F[GameState])(ifFalse: => F[GameState])(using
  GameState): F[GameState] = {
    given IfFlagged[F] = MonadIfFlagged[F]

    IfFlagged[F].perform(coord) then_ {
      ifFalse
    } else_ {
      ifTrue
    }
  }

}
