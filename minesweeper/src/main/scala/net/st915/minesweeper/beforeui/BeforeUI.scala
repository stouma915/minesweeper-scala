package net.st915.minesweeper.beforeui

import cats.effect.Sync
import net.st915.immutablescalajs.ScalaJSWindow
import net.st915.minesweeper.RunContext
import net.st915.minesweeper.beforeui.tasks.*

object BeforeUI {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](using ScalaJSWindow): F[RunContext] =
    for {
      diff <- GetDifficulty.wired[F]
    } yield RunContext(diff)

}
