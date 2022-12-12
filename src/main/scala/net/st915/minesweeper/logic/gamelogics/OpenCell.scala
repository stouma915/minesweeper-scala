package net.st915.minesweeper.logic.gamelogics

import cats.effect.Sync
import net.st915.minesweeper.{Coordinate, GameState}
import net.st915.minesweeper.logic.gamelogics.instances.*
import net.st915.minesweeper.logic.gamelogics.typeclasses.*
import net.st915.minesweeper.logic.instances.*
import net.st915.minesweeper.logic.typeclasses.*
import net.st915.minesweeper.util.instances.MonadDoNothing
import net.st915.minesweeper.util.typeclasses.DoNothing

object OpenCell {

  import cats.syntax.flatMap.*

  import net.st915.minesweeper.syntax.ifSyntax.*

  def wired[F[_]: Sync](coord: Coordinate)(using GameState): F[GameState] = {
    given CanAddOpened[F] = MonadCanAddOpened[F]

    given IfOpened[F] = MonadIfOpened[F]

    given DoNothing[F] = MonadDoNothing[F]

    IfOpened[F].perform(coord) ifTrue {
      DoNothing[F].perform
    } ifFalse {
      CanAddOpened[F].perform(coord)
    }
  }

}
