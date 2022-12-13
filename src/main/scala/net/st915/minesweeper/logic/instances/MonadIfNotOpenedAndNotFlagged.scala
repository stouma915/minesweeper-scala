package net.st915.minesweeper.logic.instances

import cats.Monad
import net.st915.minesweeper.logic.instances.*
import net.st915.minesweeper.logic.typeclasses.*
import net.st915.minesweeper.util.HigherKindIf
import net.st915.minesweeper.{Coordinate, GameState}

class MonadIfNotOpenedAndNotFlagged[F[_]: Monad] extends IfNotOpenedAndNotFlagged[F] {

  import net.st915.minesweeper.syntax.booleanSyntax.*

  override def perform(coord: Coordinate)(using GameState): HigherKindIf[F, GameState] = {
    given IsFlagged[F] = MonadIsFlagged[F]
    given IsOpened[F] = MonadIsOpened[F]

    HigherKindIf.begin(
      not(IsOpened[F].check(coord)) and
        not(IsFlagged[F].check(coord))
    )
  }

}
