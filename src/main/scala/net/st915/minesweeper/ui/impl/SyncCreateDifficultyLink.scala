package net.st915.minesweeper.ui.impl

import cats.effect.Sync
import net.st915.minesweeper.{Difficulties, Difficulty}
import net.st915.minesweeper.ui.application.CreateDifficultyLink
import org.scalajs.dom.*

class SyncCreateDifficultyLink[F[_]: Sync] extends CreateDifficultyLink[F] {

  override def create(diff: Difficulty)(implicit window: Window): F[String] =
    Sync[F].pure {
      val currentURL = new URL(window.location.href)
      val param =
        if (diff eq Difficulties.Default) "" else s"?d=${diff.id}"

      s"${currentURL.origin}${currentURL.pathname}$param"
    }

}
