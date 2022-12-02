package net.st915.minesweeper.ui.impl

import cats.effect.Sync
import net.st915.minesweeper.ui.application.*
import org.scalajs.dom.*

class SyncCreateDiv[F[_]: Sync: CreateElement] extends CreateDiv[F] {

  override def create(implicit document: HTMLDocument): F[HTMLDivElement] =
    CreateElement[F].create[HTMLDivElement]("div")

}
