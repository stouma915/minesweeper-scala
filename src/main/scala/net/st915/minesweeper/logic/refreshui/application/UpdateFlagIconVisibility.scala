package net.st915.minesweeper.logic.refreshui.application

import net.st915.minesweeper.Coordinate
import org.scalajs.dom.*

object UpdateFlagIconVisibility {

  def apply[F[_]: UpdateFlagIconVisibility]: UpdateFlagIconVisibility[F] = implicitly

}

trait UpdateFlagIconVisibility[F[_]] {

  def update(coord: Coordinate, visible: Boolean)(implicit document: HTMLDocument): F[Unit]

}
