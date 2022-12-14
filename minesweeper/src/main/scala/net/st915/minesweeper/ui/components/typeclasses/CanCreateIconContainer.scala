package net.st915.minesweeper.ui.components.typeclasses

import net.st915.minesweeper.Coordinate
import org.scalajs.dom.*

object CanCreateIconContainer {

  def apply[F[_], A](using ev: CanCreateIconContainer[F, A]): CanCreateIconContainer[F, A] = ev

}

trait CanCreateIconContainer[F[_], A] {

  def create(coord: Coordinate)(using HTMLDocument): F[HTMLDivElement]

}
