package net.st915.minesweeper

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.Coordinate

object Util {

  import cats.implicits.*
  import cats.syntax.parallel.*

  def makeListOfIO[A](
      difficulty: Difficulty,
      program: Coordinate => IO[A]
  ): List[IO[A]] =
    (0 until difficulty.width)
      .map { x =>
        (0 until difficulty.height)
          .map { y =>
            program(Coordinate(x, y))
          }
          .toList
      }
      .toList
      .flatten

  def forAllCoords[A](
      difficulty: Difficulty,
      program: Coordinate => IO[A]
  )(implicit runtime: IORuntime): IO[Unit] =
    for {
      _ <- makeListOfIO[A](difficulty, program).sequence
    } yield ()

  def forAllCoordsPar[A](
      difficulty: Difficulty,
      program: Coordinate => IO[A]
  )(implicit runtime: IORuntime): IO[Unit] =
    for {
      _ <- makeListOfIO[A](difficulty, program).parSequence
    } yield ()

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
