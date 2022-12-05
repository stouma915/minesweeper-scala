package net.st915.minesweeper.logic.refreshui.application

import net.st915.minesweeper.Coordinate
import org.scalajs.dom.*

object OpenCell {

  def apply[F[_]: OpenCell]: OpenCell[F] = implicitly

}

trait OpenCell[F[_]] {

  def perform(coord: Coordinate)(implicit document: HTMLDocument): F[Unit]

}
