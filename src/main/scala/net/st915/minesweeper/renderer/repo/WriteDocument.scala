package net.st915.minesweeper.renderer.repo

import org.scalajs.dom.{HTMLDocument, Element}

object WriteDocument {

  def apply[F[_]: WriteDocument]: WriteDocument[F] = implicitly

}

trait WriteDocument[F[_]] {

  def write(element: Element)(implicit document: HTMLDocument): F[Unit]

}
