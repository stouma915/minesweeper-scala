package net.st915.minesweeper.beforeui.tasks

import cats.effect.Sync
import net.st915.immutablescalajs.ScalaJSWindow
import net.st915.minesweeper.Difficulty

object GetDifficulty {

  import cats.syntax.flatMap.*

  def getDiffParam[F[_]: Sync](using ScalaJSWindow): F[Option[String]] =
    Sync[F].pure {
      val params = new org.scalajs.dom.URLSearchParams(summon[ScalaJSWindow].location.search)

      Option(params.get("d"))
    }

  def getDiffFromOption[F[_]: Sync](opt: Option[String]): F[Difficulty] =
    Sync[F].pure {
      opt
        .flatMap { str => Difficulty.All.find(_.id eq str) }
        .getOrElse(Difficulty.Default)
    }

  def wired[F[_]: Sync](using ScalaJSWindow): F[Difficulty] =
    getDiffParam >>= getDiffFromOption

}
