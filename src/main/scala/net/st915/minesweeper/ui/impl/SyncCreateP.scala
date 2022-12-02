package net.st915.minesweeper.ui.impl

import cats.effect.Sync
import net.st915.minesweeper.ui.application.*
import org.scalajs.dom.*

class SyncCreateP[F[_]: Sync: CreateElement] extends CreateP[F] {

  override def create(implicit document: HTMLDocument): F[HTMLParagraphElement] =
    CreateElement[F].create[HTMLParagraphElement]("p")

}
