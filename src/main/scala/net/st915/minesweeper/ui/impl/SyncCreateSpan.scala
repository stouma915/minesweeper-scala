package net.st915.minesweeper.ui.impl

import cats.effect.Sync
import net.st915.minesweeper.ui.application.*
import org.scalajs.dom.*

class SyncCreateSpan[F[_]: Sync: CreateElement] extends CreateSpan[F] {

  override def create(implicit document: HTMLDocument): F[HTMLSpanElement] =
    CreateElement[F].create[HTMLSpanElement]("span")

}
