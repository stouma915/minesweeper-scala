package net.st915.minesweeper.logic.refreshui.application

import net.st915.minesweeper.Coordinate
import org.scalajs.dom.*

object CloseCell {

  def apply[F[_]: CloseCell]: CloseCell[F] = implicitly

}

trait CloseCell[F[_]] {

  def perform(coord: Coordinate)(implicit document: HTMLDocument): F[Unit]

}
