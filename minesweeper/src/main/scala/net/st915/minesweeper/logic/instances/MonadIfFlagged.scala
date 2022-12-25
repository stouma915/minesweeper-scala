package net.st915.minesweeper.logic.instances

import cats.Monad
import net.st915.minesweeper.logic.instances.*
import net.st915.minesweeper.logic.typeclasses.*
import net.st915.minesweeper.util.HigherKindIf
import net.st915.minesweeper.{Coordinate, GameState}

class MonadIfFlagged[F[_]: Monad] extends IfFlagged[F] {

  override def perform(coord: Coordinate)(using GameState): HigherKindIf[F, GameState] = {
    given IsFlagged[F] = MonadIsFlagged[F]

    HigherKindIf.begin(IsFlagged[F].check(coord))
  }

}
