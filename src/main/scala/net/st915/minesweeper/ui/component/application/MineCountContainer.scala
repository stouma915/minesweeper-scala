package net.st915.minesweeper.ui.component.application

import org.scalajs.dom.*

object MineCountContainer {

  def apply[F[_]: MineCountContainer]: MineCountContainer[F] = implicitly

}

trait MineCountContainer[F[_]] {

  def create(
      id: String,
      num: Int
  )(implicit document: HTMLDocument): F[HTMLDivElement]

}
