package net.st915.minesweeper.ui.impl

import cats.Applicative
import net.st915.minesweeper.ui.application.CreateDifficultyLink
import net.st915.minesweeper.{Consts, Difficulties, Difficulty}
import org.scalajs.dom.*

class ApplicativeCreateDifficultyLink[F[_]: Applicative] extends CreateDifficultyLink[F] {

  override def create(diff: Difficulty)(implicit window: Window): F[String] =
    Applicative[F].pure {
      val currentURL = new URL(window.location.href)
      val param =
        if (diff eq Difficulties.Default)
          ""
        else
          s"?${Consts.DifficultyParameter}=${diff.id}"

      s"${currentURL.origin}${currentURL.pathname}$param"
    }

}
