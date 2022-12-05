package net.st915.minesweeper.logic.refreshui.application

import net.st915.minesweeper.Coordinate
import org.scalajs.dom.*

object UpdateCellOpening {

  def apply[F[_]: UpdateCellOpening]: UpdateCellOpening[F] = implicitly

}

trait UpdateCellOpening[F[_]] {

  def update(coord: Coordinate, opening: Boolean)(implicit document: HTMLDocument): F[Unit]

}
