package net.st915.minesweeper.beforeui

import cats.effect.Sync
import net.st915.minesweeper.RunContext
import net.st915.minesweeper.beforeui.task.*
import org.scalajs.dom.*

object BeforeUI {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](implicit window: Window): F[RunContext] =
    BeforeUI()

  def apply[F[_]: Sync]()(implicit window: Window): F[RunContext] =
    for {
      diff <- GetDifficulty.wired[F]
      context <- Sync[F].pure {
        RunContext(diff)
      }
    } yield context

}
