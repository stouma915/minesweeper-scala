package net.st915.minesweeper.ui.application

import org.scalajs.dom.*

object AppendTextNode {

  def apply[F[_]: AppendTextNode]: AppendTextNode[F] = implicitly

}

trait AppendTextNode[F[_]] {

  def append[A <: HTMLElement](
      parent: A,
      child: String
  )(implicit document: HTMLDocument): F[Unit]

}
