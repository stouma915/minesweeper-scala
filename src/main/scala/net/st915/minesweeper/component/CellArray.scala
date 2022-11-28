package net.st915.minesweeper.component

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.implicits.*
import net.st915.minesweeper.{Coordinate, Difficulty}
import org.scalajs.dom.*

import scala.util.chaining.*

object CellArray {

  def make(
      difficulty: Difficulty,
      onCellClick: Coordinate => IO[Unit],
      onCellRightClick: Coordinate => IO[Unit]
  )(implicit doc: Document, runtime: IORuntime): IO[Element] =
    IO {
      doc
        .createElement("div")
        .tap(_.classList.add("cellArray"))
        .tap { div =>
          (0 until difficulty.height).foreach { y =>
            doc
              .createElement("div")
              .tap(_.classList.add("line"))
              .tap { lineDiv =>
                (0 until difficulty.width).foreach { x =>
                  val coord = Coordinate(x, y)

                  val program = for {
                    cell <- Cell.make(coord, onCellClick, onCellRightClick)
                    _ <- IO(lineDiv.appendChild(cell))
                  } yield ()

                  program.unsafeRunAndForget()
                }
              }
              .tap(div.appendChild)
          }
        }
    }

}
