package net.st915.minesweeper.ui.component.application

import org.scalajs.dom.*

object FlagPlaceholderIcon {

  def apply[F[_]: FlagPlaceholderIcon]: FlagPlaceholderIcon[F] = implicitly

}

trait FlagPlaceholderIcon[F[_]] {

  def create(implicit document: HTMLDocument): F[HTMLDivElement]

}
