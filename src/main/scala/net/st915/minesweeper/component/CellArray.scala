package net.st915.minesweeper.component

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.implicits.*
import net.st915.minesweeper.{Coordinate, Difficulty}
import org.scalajs.dom.*

import scala.util.chaining.*

object CellArray {

  import cats.implicits.*

  def make(
      difficulty: Difficulty,
      onCellClick: Coordinate => IO[Unit],
      onCellRightClick: Coordinate => IO[Unit]
  )(implicit doc: Document, runtime: IORuntime): IO[Element] =
    for {
      lines <- {
        (0 until difficulty.height)
          .toList
          .map { y =>
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
              line <- IO {
                doc
                  .createElement("div")
                  .tap(_.classList.add("line"))
              }
              _ <- {
                cells
                  .map { cell =>
                    IO {
                      line.appendChild(cell)
                    }
                  }
                  .sequence
              }
            } yield line
          }
          .sequence
      }
      component <- IO {
        doc
          .createElement("div")
          .tap(_.classList.add("cellArray"))
      }
      _ <- {
        lines
          .map { line =>
            IO {
              component.appendChild(line)
            }
          }
          .sequence
      }
    } yield component

}
