package net.st915.minesweeper.ui.components.typeclasses

import org.scalajs.dom.*

object CanAppendElement {

  def apply[F[_]](using ev: CanAppendElement[F]): CanAppendElement[F] = ev

}

trait CanAppendElement[F[_]] {

  def perform(parent: HTMLElement, child: HTMLElement): F[Unit]

}
