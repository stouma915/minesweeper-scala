package net.st915.minesweeper.ui.components.typeclasses

import net.st915.minesweeper.util.CSSClass
import org.scalajs.dom.*

object CanUpdateElementClass {

  def apply[F[_]](using ev: CanUpdateElementClass[F]): CanUpdateElementClass[F] = ev

}

trait CanUpdateElementClass[F[_]] {

  def perform(element: HTMLElement, newClass: CSSClass): F[Unit]

}
