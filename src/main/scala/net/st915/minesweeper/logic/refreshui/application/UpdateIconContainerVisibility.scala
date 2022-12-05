package net.st915.minesweeper.logic.refreshui.application

import net.st915.minesweeper.Coordinate
import org.scalajs.dom.*

object UpdateIconContainerVisibility {

  def apply[F[_]: UpdateIconContainerVisibility]: UpdateIconContainerVisibility[F] = implicitly

}

trait UpdateIconContainerVisibility[F[_]] {

  def update(iconClass: String, coord: Coordinate, visible: Boolean)(implicit
  document: HTMLDocument): F[Unit]

}
