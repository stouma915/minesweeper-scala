package net.st915.minesweeper.component

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.event.*
import net.st915.minesweeper.implicits.*
import net.st915.minesweeper.{Constants, Coordinate, Difficulty}
import org.scalajs.dom.*

import scala.util.chaining.*

object GameScreen {

  private def onClick(coord: Coordinate): IO[Unit] =
    for {
      event <- IO(CellClickEvent(coord))
      _ <- EventQueue.queue(event)
    } yield ()

  private def onRightClick(coord: Coordinate): IO[Unit] =
    for {
      event <- IO(CellRightClickEvent(coord))
      _ <- EventQueue.queue(event)
    } yield ()

  private def onFlagPlaceButtonClick: IO[Unit] =
    for {
      event <- IO(ButtonClickEvent(Constants.FlagPlaceButtonId))
      _ <- EventQueue.queue(event)
    } yield ()

  private def onRestartButtonClick: IO[Unit] =
    for {
      event <- IO(ButtonClickEvent(Constants.RestartButtonId))
      _ <- EventQueue.queue(event)
    } yield ()

  def make(
      difficulty: Difficulty
  )(implicit doc: Document, runtime: IORuntime): IO[Element] = for {
    flagPlaceButton <- Button.make(
      Constants.NotFlagPlaceModeText,
      Constants.FlagPlaceButtonId,
      onFlagPlaceButtonClick
    )
    restartButton <- Button.make(
      "Restart",
      Constants.RestartButtonId,
      onRestartButtonClick
    )
    component <- IO {
      doc
        .createElement("div")
        .tap { div =>
          doc
            .createElement("div")
            .tap(_.classList.add("gameScreen"))
            .tap { cellsDiv =>
              (0 until difficulty.height).foreach { y =>
                doc
                  .createElement("div")
                  .tap(_.classList.add("line"))
                  .tap { lineDiv =>
                    (0 until difficulty.width).foreach { x =>
                      val coord = Coordinate(x, y)

                      val program = for {
                        cell <- Cell.make(coord, onClick, onRightClick)
                        _ <- IO(lineDiv.appendChild(cell))
                      } yield ()

                      program.unsafeRunAndForget()
                    }
                  }
                  .tap(cellsDiv.appendChild)
              }
            }
            .tap(div.appendChild)
        }
        .tap(_.appendChild(doc.makeBR))
        .tap(_.appendChild(flagPlaceButton))
        .tap(_.appendChild(doc.makeBR))
        .tap(_.appendChild(restartButton))
        .tap(_.appendChild(doc.makeBR))
    }
  } yield component

}
