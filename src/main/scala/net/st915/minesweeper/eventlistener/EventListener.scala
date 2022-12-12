package net.st915.minesweeper.eventlistener

import cats.effect.Sync
import net.st915.minesweeper.GameState
import net.st915.minesweeper.event.*
import net.st915.minesweeper.eventlistener.tasks.*
import net.st915.minesweeper.ui.consts.IDs
import net.st915.minesweeper.util.instances.MonadDoNothing
import net.st915.minesweeper.util.typeclasses.DoNothing

object EventListener {

  import cats.syntax.flatMap.*

  def wired[F[_]: Sync](event: Event)(using GameState): F[GameState] = {
    given DoNothing[F] = MonadDoNothing[F]

    event match
      case e: CellClickEvent =>
        CellClickEventListener.wired[F](e)
      case e: CellRightClickEvent =>
        CellRightClickEventListener.wired[F](e)
      case ButtonClickEvent(id) =>
        id match
          case IDs.ToggleFlagModeButtonId =>
            ToggleFlagModeButtonClickEventListener.wired[F]
          case IDs.RestartButtonId =>
            RestartButtonClickEventListener.wired[F]
          case _ => DoNothing[F].perform
      case _ => DoNothing[F].perform
  }

}
