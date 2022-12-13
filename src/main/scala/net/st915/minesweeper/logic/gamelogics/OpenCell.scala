package net.st915.minesweeper.logic.gamelogics

import cats.effect.Sync
import net.st915.minesweeper.logic.gamelogics.instances.*
import net.st915.minesweeper.logic.gamelogics.typeclasses.*
import net.st915.minesweeper.logic.instances.*
import net.st915.minesweeper.logic.typeclasses.*
import net.st915.minesweeper.util.instances.MonadDoNothing
import net.st915.minesweeper.util.typeclasses.DoNothing
import net.st915.minesweeper.{Coordinate, GameState}

object OpenCell {

  def wired[F[_]: Sync](coord: Coordinate)(using GameState): F[GameState] = {
    given CanAddOpened[F] = MonadCanAddOpened[F]

    given IfNotOpenedAndNotFlagged[F] = MonadIfNotOpenedAndNotFlagged[F]

    given DoNothing[F] = MonadDoNothing[F]

    IfNotOpenedAndNotFlagged[F].perform(coord) then_ {
      CanAddOpened[F].perform(coord)
    } else_ {
      DoNothing[F].perform
    }
  }

}
