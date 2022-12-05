package net.st915.minesweeper.logic.refreshui.application

import net.st915.minesweeper.Coordinate
import org.scalajs.dom.*

object UpdateMineIconVisibility {

  def apply[F[_]: UpdateMineIconVisibility]: UpdateMineIconVisibility[F] = implicitly

}

trait UpdateMineIconVisibility[F[_]] {

  def update(coord: Coordinate, visible: Boolean)(implicit document: HTMLDocument): F[Unit]

}
