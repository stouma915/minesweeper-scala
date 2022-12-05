package net.st915.minesweeper.util.application

import net.st915.minesweeper.{Coordinate, Difficulty, GameState}

object MineCountCalculator {

  def apply[F[_]: MineCountCalculator]: MineCountCalculator[_] = implicitly

}

trait MineCountCalculator[F[_]] {

  def calculate(coord: Coordinate, difficulty: Difficulty)(implicit gameState: GameState): F[Int]

}
