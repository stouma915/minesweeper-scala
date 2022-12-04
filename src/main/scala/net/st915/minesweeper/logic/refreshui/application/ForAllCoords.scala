package net.st915.minesweeper.logic.refreshui.application

import net.st915.minesweeper.{Coordinate, Difficulty}
import org.scalajs.dom.*

object ForAllCoords {

  def apply[F[_]: ForAllCoords]: ForAllCoords[F] = implicitly

}

trait ForAllCoords[F[_]] {

  def perform(difficulty: Difficulty, program: Coordinate => F[Unit])(implicit document: HTMLDocument): F[List[Unit]]

}
