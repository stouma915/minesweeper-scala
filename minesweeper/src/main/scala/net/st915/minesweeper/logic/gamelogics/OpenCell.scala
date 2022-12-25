package net.st915.minesweeper.logic.gamelogics

import cats.effect.Sync
import net.st915.minesweeper.logic.gamelogics.instances.*
import net.st915.minesweeper.logic.gamelogics.typeclasses.*
import net.st915.minesweeper.logic.instances.*
import net.st915.minesweeper.logic.typeclasses.*
import net.st915.minesweeper.util.DoNothing
import net.st915.minesweeper.{Coordinate, GameState}

object OpenCell {

  import net.st915.minesweeper.util.instances.doNothingInstances.given

  def wired[F[_]: Sync](coord: Coordinate)(using GameState): F[GameState] = {
    given CanAddOpened[F] = MonadCanAddOpened[F]

    given IfNotOpenedAndNotFlagged[F] = MonadIfNotOpenedAndNotFlagged[F]

    IfNotOpenedAndNotFlagged[F].perform(coord) then_ {
      CanAddOpened[F].perform(coord)
    } else_ {
      DoNothing[F]()
    }
  }

}
