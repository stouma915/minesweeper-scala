package net.st915.minesweeper.ui.impl

import cats.effect.Sync
import net.st915.minesweeper.ui.application.CreateElement
import org.scalajs.dom.{HTMLDocument, HTMLElement}

class SyncCreateElement[F[_]: Sync] extends CreateElement[F] {

  override def create[A <: HTMLElement](
      tagName: String
  )(implicit document: HTMLDocument): F[A] =
    Sync[F].pure(document.createElement(tagName).asInstanceOf[A])

}
