package net.st915.minesweeper.component

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.{Coordinate, Difficulty}
import org.scalajs.dom.*

import scala.util.chaining.*

object CellLine {

  import cats.implicits.*

  def make(
    y: Int,
    difficulty: Difficulty,
    onCellClick: Coordinate => IO[Unit],
    onCellRightClick: Coordinate => IO[Unit]
  )(implicit doc: Document, runtime: IORuntime): IO[Element] =
    for {
      cells <- {
        (0 until difficulty.width)
          .toList
          .map { x =>
            Cell.make(
              Coordinate(x, y),
              onCellClick,
              onCellRightClick
            )
          }
          .sequence
      }
      component <- IO {
        doc
          .createElement("div")
          .tap(_.classList.add("line"))
      }
      _ <- {
        cells
          .map { cell =>
            IO {
              component.appendChild(cell)
            }
          }
          .sequence
      }
    } yield component

}
