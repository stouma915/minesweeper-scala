package net.st915.minesweeper.ui.component.application

import org.scalajs.dom.*

object InformationText {

  def apply[F[_]: InformationText]: InformationText[F] = implicitly

}

trait InformationText[F[_]] {

  def create(implicit document: HTMLDocument): F[HTMLDivElement]

}
