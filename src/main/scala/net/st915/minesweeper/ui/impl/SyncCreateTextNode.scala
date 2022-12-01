package net.st915.minesweeper.ui.impl

import cats.effect.Sync
import net.st915.minesweeper.ui.application.CreateTextNode
import org.scalajs.dom.{HTMLDocument, Text}

class SyncCreateTextNode[F[_]: Sync] extends CreateTextNode[F] {

  override def create(text: String)(implicit document: HTMLDocument): F[Text] =
    Sync[F].pure(document.createTextNode(text))

}
