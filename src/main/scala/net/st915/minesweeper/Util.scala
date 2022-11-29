package net.st915.minesweeper

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.Coordinate

object Util {

  def forAllCoords(
      difficulty: Difficulty,
      program: Coordinate => IO[Unit]
  )(implicit runtime: IORuntime): IO[Unit] = IO {
    (0 until difficulty.width).foreach { x =>
      (0 until difficulty.height).foreach { y =>
        val coord = Coordinate(x, y)

        program(coord).unsafeRunAndForget()
      }
    }
  }

  def get3x3(
      coord: Coordinate,
      difficulty: Difficulty
  ): List[Coordinate] = {
    val x = coord.x
    val y = coord.y
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
