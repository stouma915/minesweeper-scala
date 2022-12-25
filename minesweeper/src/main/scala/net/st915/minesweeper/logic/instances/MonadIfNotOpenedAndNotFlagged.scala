package net.st915.minesweeper.logic.instances

import cats.Monad
import net.st915.minesweeper.logic.instances.*
import net.st915.minesweeper.logic.typeclasses.*
import net.st915.minesweeper.{Coordinate, GameState}
import net.st915.minesweeper.util.HigherKindIf

class MonadIfNotOpenedAndNotFlagged[F[_]: Monad] extends IfNotOpenedAndNotFlagged[F] {

  import net.st915.minesweeper.syntax.booleanSyntax.*
  import net.st915.minesweeper.syntax.haskellLikeSyntax.*

  override def perform(coord: Coordinate)(using GameState): HigherKindIf[F, GameState] = {
    given IsFlagged[F] = MonadIsFlagged[F]
    given IsOpened[F] = MonadIsOpened[F]

    HigherKindIf.begin(
      (not[F] $ IsOpened[F].check(coord)) and
        (not[F] $ IsFlagged[F].check(coord))
    )
  }

}
