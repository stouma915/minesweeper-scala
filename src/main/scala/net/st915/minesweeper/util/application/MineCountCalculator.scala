package net.st915.minesweeper.util.application

import net.st915.minesweeper.{Coordinate, Difficulty, GameState}
import org.scalajs.dom.*

object MineCountCalculator {

  def apply[F[_]: MineCountCalculator]: MineCountCalculator[_] = implicitly

}

trait MineCountCalculator[F[_]] {

  def calculate(coord: Coordinate, difficulty: Difficulty)(implicit gameState: GameState): F[Int]

  def calculateAll(difficulty: Difficulty)(
    implicit gameState: GameState,
    document: HTMLDocument
  ): F[Map[Coordinate, Int]]

}
