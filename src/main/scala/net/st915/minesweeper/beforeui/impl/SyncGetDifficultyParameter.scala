package net.st915.minesweeper.beforeui.impl

import cats.effect.Sync
import net.st915.minesweeper.beforeui.application.GetDifficultyParameter
import org.scalajs.dom.*

class SyncGetDifficultyParameter[F[_]: Sync] extends GetDifficultyParameter[F] {

  override def get(implicit window: Window): F[Option[ByteString]] =
    Sync[F].pure {
      val params = new URLSearchParams(window.location.search)

      if (params.has("d"))
        Some(params.get("d"))
      else
        None
    }

}
