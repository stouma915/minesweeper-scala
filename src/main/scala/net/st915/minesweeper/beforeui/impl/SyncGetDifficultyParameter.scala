package net.st915.minesweeper.beforeui.impl

import cats.effect.Sync
import net.st915.minesweeper.Consts
import net.st915.minesweeper.beforeui.application.GetDifficultyParameter
import org.scalajs.dom.*

class SyncGetDifficultyParameter[F[_]: Sync] extends GetDifficultyParameter[F] {

  override def get(implicit window: Window): F[Option[String]] =
    Sync[F].pure {
      val params = new URLSearchParams(window.location.search)

      if (params.has(Consts.DifficultyParameter))
        Some(params.get(Consts.DifficultyParameter))
      else
        None
    }

}
