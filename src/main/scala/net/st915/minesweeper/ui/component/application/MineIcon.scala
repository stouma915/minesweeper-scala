package net.st915.minesweeper.ui.component.application

import org.scalajs.dom.*

object MineIcon {

  def apply[F[_]: MineIcon]: MineIcon[F] = implicitly

}

trait MineIcon[F[_]] {

  def create(implicit document: HTMLDocument): F[HTMLDivElement]

}
