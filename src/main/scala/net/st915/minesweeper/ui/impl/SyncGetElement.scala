package net.st915.minesweeper.ui.impl

import cats.effect.Sync
import net.st915.minesweeper.ui.application.GetElement
import org.scalajs.dom.*

class SyncGetElement[F[_]: Sync] extends GetElement[F] {

  override def get[A <: HTMLElement](id: String)(implicit document: HTMLDocument): F[A] =
    Sync[F].blocking(document.getElementById(id).asInstanceOf[A])

}
