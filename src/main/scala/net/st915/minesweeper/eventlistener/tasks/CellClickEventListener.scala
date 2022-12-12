package net.st915.minesweeper.eventlistener.tasks

import cats.effect.Sync
import net.st915.minesweeper.GameState
import net.st915.minesweeper.event.CellClickEvent
import net.st915.minesweeper.util.instances.MonadDoNothing
import net.st915.minesweeper.util.typeclasses.DoNothing

object CellClickEventListener {

  import cats.syntax.flatMap.*

  def wired[F[_]: Sync](event: CellClickEvent)(using GameState): F[GameState] = {
    given DoNothing[F] = MonadDoNothing[F]

    Sync[F].pure(println(s"cell clicked: ${event.coord}")) >>
      DoNothing[F].perform
  }

}
