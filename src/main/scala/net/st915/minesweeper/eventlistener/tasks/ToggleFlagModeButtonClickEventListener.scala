package net.st915.minesweeper.eventlistener.tasks

import cats.effect.Sync
import net.st915.minesweeper.GameState
import net.st915.minesweeper.logic.gamelogics.ToggleFlagPlaceMode

object ToggleFlagModeButtonClickEventListener {

  def wired[F[_]: Sync](using GameState): F[GameState] =
    ToggleFlagPlaceMode.wired[F]

}
