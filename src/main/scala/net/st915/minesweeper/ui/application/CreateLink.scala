package net.st915.minesweeper.ui.application

import org.scalajs.dom.*

object CreateLink {

  def apply[F[_]: CreateLink]: CreateLink[F] = implicitly

}

trait CreateLink[F[_]] {

  def create(implicit document: HTMLDocument): F[HTMLLinkElement]

}
