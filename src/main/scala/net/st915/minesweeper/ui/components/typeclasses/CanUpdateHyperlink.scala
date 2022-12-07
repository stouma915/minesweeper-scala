package net.st915.minesweeper.ui.components.typeclasses

import net.st915.minesweeper.util.Link
import org.scalajs.dom.*

object CanUpdateHyperlink {

  def apply[F[_]](using ev: CanUpdateHyperlink[F]): CanUpdateHyperlink[F] = ev

}

trait CanUpdateHyperlink[F[_]] {

  def perform(element: HTMLLinkElement, link: Link): F[Unit]

}
