package net.st915.minesweeper.ui.impl

import cats.effect.Sync
import net.st915.minesweeper.ui.application.*
import org.scalajs.dom.*

class SyncCreateBR[F[_]: Sync: CreateElement] extends CreateBR[F] {

  override def create(implicit document: HTMLDocument): F[HTMLBRElement] =
    CreateElement[F].create[HTMLBRElement]("br")

}
