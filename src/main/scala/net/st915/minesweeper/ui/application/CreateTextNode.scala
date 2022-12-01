package net.st915.minesweeper.ui.application

import org.scalajs.dom.{HTMLDocument, Text}

object CreateTextNode {

  def apply[F[_]: CreateTextNode]: CreateTextNode[F] = implicitly

}

trait CreateTextNode[F[_]] {

  def create(text: String)(implicit document: HTMLDocument): F[Text]

}
