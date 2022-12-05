package net.st915.minesweeper.util.application

import net.st915.minesweeper.{Coordinate, Difficulty}

object MineGenerator {

  def apply[F[_]: MineGenerator]: MineGenerator[F] = implicitly

}

trait MineGenerator[F[_]] {

  def perform(startPoint: Coordinate, difficulty: Difficulty): F[List[Coordinate]]

}
