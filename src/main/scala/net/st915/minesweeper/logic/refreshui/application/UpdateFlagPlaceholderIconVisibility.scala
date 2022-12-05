package net.st915.minesweeper.logic.refreshui.application

import net.st915.minesweeper.Coordinate
import org.scalajs.dom.*

object UpdateFlagPlaceholderIconVisibility {

  def apply[F[_]: UpdateFlagPlaceholderIconVisibility]: UpdateFlagPlaceholderIconVisibility[F] =
    implicitly

}

trait UpdateFlagPlaceholderIconVisibility[F[_]] {

  def update(coord: Coordinate, visible: Boolean)(implicit document: HTMLDocument): F[Unit]

}
