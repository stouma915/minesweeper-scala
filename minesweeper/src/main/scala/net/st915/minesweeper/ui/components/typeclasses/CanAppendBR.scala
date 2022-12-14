package net.st915.minesweeper.ui.components.typeclasses

import org.scalajs.dom.*

object CanAppendBR {

  def apply[F[_]](using ev: CanAppendBR[F]): CanAppendBR[F] = ev

}

trait CanAppendBR[F[_]] {

  def perform(parent: HTMLElement)(using HTMLDocument): F[Unit]

}
