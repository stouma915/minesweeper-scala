package net.st915.minesweeper.ui.application

import org.scalajs.dom.{HTMLDocument, HTMLElement}

object AppendTextNode {

  def apply[F[_]: AppendTextNode]: AppendTextNode[F] = implicitly

}

trait AppendTextNode[F[_]] {

  def append[A <: HTMLElement](
      parent: A,
      child: String
  )(implicit document: HTMLDocument): F[Unit]

}
