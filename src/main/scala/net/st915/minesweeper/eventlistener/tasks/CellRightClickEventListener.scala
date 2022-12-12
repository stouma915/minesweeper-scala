package net.st915.minesweeper.eventlistener.tasks

import cats.effect.Sync
import net.st915.minesweeper.GameState
import net.st915.minesweeper.event.CellRightClickEvent
import net.st915.minesweeper.util.instances.MonadDoNothing
import net.st915.minesweeper.util.typeclasses.DoNothing

object CellRightClickEventListener {

  import cats.syntax.flatMap.*

  def wired[F[_]: Sync](event: CellRightClickEvent)(using GameState): F[GameState] = {
    given DoNothing[F] = MonadDoNothing[F]

    Sync[F].pure(println(s"cell right clicked: ${event.coord}")) >>
      DoNothing[F].perform
  }

}
