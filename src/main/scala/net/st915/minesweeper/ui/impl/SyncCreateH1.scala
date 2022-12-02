package net.st915.minesweeper.ui.impl

import cats.effect.Sync
import net.st915.minesweeper.ui.application.*
import org.scalajs.dom.*

class SyncCreateH1[F[_]: Sync: CreateElement] extends CreateH1[F] {

  override def create(implicit document: HTMLDocument): F[HTMLElement] =
    CreateElement[F].create[HTMLElement]("h1")

}
