package net.st915.minesweeper.beforeui.tasks.instances

import cats.effect.Sync
import net.st915.minesweeper.Consts
import net.st915.minesweeper.beforeui.tasks.typeclasses.CanGetDifficultyParameter
import org.scalajs.dom.*

class SyncCanGetDifficultyParameter[F[_]: Sync] extends CanGetDifficultyParameter[F] {

  override def get(using Window): F[Option[String]] =
    Sync[F].pure {
      val params = new URLSearchParams(summon[Window].location.search)

      if (params.has(Consts.DifficultyParameter))
        Some(params.get(Consts.DifficultyParameter))
      else
        None
    }

}
