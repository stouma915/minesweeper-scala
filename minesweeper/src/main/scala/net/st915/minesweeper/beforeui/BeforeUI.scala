package net.st915.minesweeper.beforeui

import cats.effect.Sync
import net.st915.minesweeper.RunContext
import net.st915.minesweeper.beforeui.tasks.*
import org.scalajs.dom.*

object BeforeUI {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](using Window): F[RunContext] =
    for {
      diff <- GetDifficulty.wired[F]

      runContext <- Sync[F].pure {
        RunContext(diff)
      }
    } yield runContext

}
