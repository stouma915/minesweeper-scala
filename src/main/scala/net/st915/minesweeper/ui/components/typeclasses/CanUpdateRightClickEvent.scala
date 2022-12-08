package net.st915.minesweeper.ui.components.typeclasses

import cats.effect.unsafe.IORuntime
import org.scalajs.dom.*

object CanUpdateRightClickEvent {

  def apply[F[_]](using ev: CanUpdateRightClickEvent[F]): CanUpdateRightClickEvent[F] = ev

}

trait CanUpdateRightClickEvent[F[_]] {

  def perform(element: HTMLElement, program: => F[Unit])(using IORuntime): F[Unit]

}
