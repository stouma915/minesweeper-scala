package net.st915.minesweeper.logic.gamelogics

import cats.effect.Sync
import net.st915.minesweeper.GameState
import net.st915.minesweeper.logic.gamelogics.instances.*
import net.st915.minesweeper.logic.gamelogics.typeclasses.*
import net.st915.minesweeper.logic.instances.*
import net.st915.minesweeper.logic.typeclasses.*

object ToggleFlagPlaceMode {

  import net.st915.minesweeper.syntax.ifSyntax.*

  def wired[F[_]: Sync](using GameState): F[GameState] = {
    given CanEnterFlagPlaceMode[F] = MonadCanEnterFlagPlaceMode[F]
    given CanExitFlagPlaceMode[F] = MonadCanExitFlagPlaceMode[F]

    given IfInFlagPlaceMode[F] = MonadIfInFlagPlaceMode[F]

    IfInFlagPlaceMode[F].perform then_ {
      CanExitFlagPlaceMode[F].perform
    } else_ {
      CanEnterFlagPlaceMode[F].perform
    }
  }

}
