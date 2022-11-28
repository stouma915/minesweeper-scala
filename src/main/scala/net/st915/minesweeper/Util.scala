package net.st915.minesweeper

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.Coordinate
import net.st915.minesweeper.difficulty.Difficulty

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

}
