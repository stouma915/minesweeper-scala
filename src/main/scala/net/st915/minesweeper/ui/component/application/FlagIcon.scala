package net.st915.minesweeper.ui.component.application

import org.scalajs.dom.*

object FlagIcon {

  def apply[F[_]: FlagIcon]: FlagIcon[F] = implicitly

}

trait FlagIcon[F[_]] {

  def create(implicit document: HTMLDocument): F[HTMLDivElement]

}
