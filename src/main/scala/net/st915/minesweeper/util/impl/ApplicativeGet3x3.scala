package net.st915.minesweeper.util.impl

import cats.Applicative
import net.st915.minesweeper.util.application.Get3x3
import net.st915.minesweeper.{Coordinate, Difficulty}

class ApplicativeGet3x3[F[_]: Applicative] extends Get3x3[F] {

  override def get(center: Coordinate, difficulty: Difficulty): F[List[Coordinate]] =
    Applicative[F].pure {
      val x = center.x
      val y = center.y
      val coords = Seq(
        Coordinate(x - 1, y - 1),
        Coordinate(x - 1, y),
        Coordinate(x - 1, y + 1),
        Coordinate(x, y - 1),
        Coordinate(x, y),
        Coordinate(x, y + 1),
        Coordinate(x + 1, y - 1),
        Coordinate(x + 1, y),
        Coordinate(x + 1, y + 1)
      )

      coords
        .filter(_.x >= 0)
        .filter(_.y >= 0)
        .filter(_.x < difficulty.width)
        .filter(_.y < difficulty.height)
        .toList
    }

}
