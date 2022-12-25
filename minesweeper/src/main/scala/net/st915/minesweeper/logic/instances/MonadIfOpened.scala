package net.st915.minesweeper.logic.instances

import cats.Monad
import net.st915.minesweeper.logic.instances.*
import net.st915.minesweeper.logic.typeclasses.*
import net.st915.minesweeper.{Coordinate, GameState}
import net.st915.minesweeper.util.HigherKindIf

class MonadIfOpened[F[_]: Monad] extends IfOpened[F] {

  override def perform(coord: Coordinate)(using GameState): HigherKindIf[F, GameState] = {
    given IsOpened[F] = MonadIsOpened[F]

    HigherKindIf.begin(IsOpened[F].check(coord))
  }

}
