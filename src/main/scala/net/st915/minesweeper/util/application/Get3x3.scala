package net.st915.minesweeper.util.application

import net.st915.minesweeper.{Coordinate, Difficulty}

object Get3x3 {

  def apply[F[_]: Get3x3]: Get3x3[F] = implicitly

}

trait Get3x3[F[_]] {

  def get(center: Coordinate, difficulty: Difficulty): F[List[Coordinate]]

}
