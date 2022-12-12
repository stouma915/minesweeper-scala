package net.st915.minesweeper.logic.gamelogics

import cats.effect.Sync
import net.st915.minesweeper.{Coordinate, GameState}
import net.st915.minesweeper.logic.gamelogics.instances.*
import net.st915.minesweeper.logic.gamelogics.typeclasses.*
import net.st915.minesweeper.logic.instances.*
import net.st915.minesweeper.logic.typeclasses.*
import net.st915.minesweeper.util.instances.MonadDoNothing
import net.st915.minesweeper.util.typeclasses.DoNothing

object ToggleFlagged {

  import net.st915.minesweeper.syntax.ifSyntax.*

  def wired[F[_]: Sync](coord: Coordinate)(using GameState): F[GameState] = {
    given CanAddFlagged[F] = MonadCanAddFlagged[F]
    given CanRemoveFlagged[F] = MonadCanRemoveFlagged[F]

    given IfNotFlagged[F] = MonadIfNotFlagged[F]
    given IfNotOpened[F] = MonadIfNotOpened[F]

    given DoNothing[F] = MonadDoNothing[F]

    IfNotOpened[F].perform(coord) then_ {
      IfNotFlagged[F].perform(coord) then_ {
        CanAddFlagged[F].perform(coord)
      } else_ {
        CanRemoveFlagged[F].perform(coord)
      }
    } else_ {
      DoNothing[F].perform
    }
  }

}
