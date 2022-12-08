package net.st915.minesweeper.ui.components.typeclasses

import cats.effect.unsafe.IORuntime
import org.scalajs.dom.*

object CanUpdateClickEvent {

  def apply[F[_]](using ev: CanUpdateClickEvent[F]): CanUpdateClickEvent[F] = ev

}

trait CanUpdateClickEvent[F[_]] {

  def perform(element: HTMLElement, program: => F[Unit])(using IORuntime): F[Unit]

}
