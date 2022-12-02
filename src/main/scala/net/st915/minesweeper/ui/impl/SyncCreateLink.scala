package net.st915.minesweeper.ui.impl

import cats.effect.Sync
import net.st915.minesweeper.ui.application.*
import org.scalajs.dom.*

class SyncCreateLink[F[_]: Sync: CreateElement] extends CreateLink[F] {

  override def create(implicit document: HTMLDocument): F[HTMLLinkElement] =
    CreateElement[F].create[HTMLLinkElement]("a")

}
