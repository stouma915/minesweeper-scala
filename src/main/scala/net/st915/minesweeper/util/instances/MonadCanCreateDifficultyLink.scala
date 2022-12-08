package net.st915.minesweeper.util.instances

import cats.Monad
import net.st915.minesweeper.util.Link
import net.st915.minesweeper.util.typeclasses.CanCreateDifficultyLink
import net.st915.minesweeper.{Consts, Difficulty}
import org.scalajs.dom.*

class MonadCanCreateDifficultyLink[F[_]: Monad] extends CanCreateDifficultyLink[F] {

  override def create(diff: Difficulty)(using Window): F[Link] =
    Monad[F].pure {
      val currentURL = new URL(summon[Window].location.href)
      val param =
        if (diff eq Consts.Difficulties.Default)
          ""
        else
          s"?${Consts.DifficultyParameter}=${diff.id}"

      Link(s"${currentURL.origin}${currentURL.pathname}$param")
    }

}
