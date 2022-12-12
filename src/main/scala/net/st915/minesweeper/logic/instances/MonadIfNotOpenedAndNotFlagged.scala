package net.st915.minesweeper.logic.instances

import cats.Monad
import net.st915.minesweeper.logic.instances.*
import net.st915.minesweeper.logic.typeclasses.*
import net.st915.minesweeper.{Coordinate, GameState}

class MonadIfNotOpenedAndNotFlagged[F[_]: Monad] extends IfNotOpenedAndNotFlagged[F] {

  import net.st915.minesweeper.syntax.ifSyntax.*

  override def perform(coord: Coordinate)(ifTrue: => F[GameState])(ifFalse: => F[GameState])(
    using GameState
  ): F[GameState] = {
    given IfNotFlagged[F] = MonadIfNotFlagged[F]
    given IfNotOpened[F] = MonadIfNotOpened[F]

    IfNotOpened[F].perform(coord) then_ {
      IfNotFlagged[F].perform(coord) then_ {
        ifTrue
      } else_ {
        ifFalse
      }
    } else_ {
      ifFalse
    }
  }

}
