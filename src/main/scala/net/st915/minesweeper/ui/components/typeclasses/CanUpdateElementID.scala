package net.st915.minesweeper.ui.components.typeclasses

import net.st915.minesweeper.ui.consts.ID
import org.scalajs.dom.*

object CanUpdateElementID {

  def apply[F[_]](using ev: CanUpdateElementID[F]): CanUpdateElementID[F] = ev

}

trait CanUpdateElementID[F[_]] {

  def perform(element: HTMLElement, newID: ID): F[Unit]

}
