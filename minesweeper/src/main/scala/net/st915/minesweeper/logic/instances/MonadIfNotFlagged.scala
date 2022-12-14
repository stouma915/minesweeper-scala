package net.st915.minesweeper.logic.instances

import cats.Monad
import net.st915.minesweeper.logic.instances.*
import net.st915.minesweeper.logic.typeclasses.*
import net.st915.minesweeper.{Coordinate, GameState}
import net.st915.util.HigherKindIf

class MonadIfNotFlagged[F[_]: Monad] extends IfNotFlagged[F] {

  import net.st915.syntax.booleanSyntax.*
  import net.st915.syntax.haskellLikeSyntax.*

  override def perform(coord: Coordinate)(using GameState): HigherKindIf[F, GameState] = {
    given IsFlagged[F] = MonadIsFlagged[F]

    HigherKindIf.begin(not[F] $ IsFlagged[F].check(coord))
  }

}
