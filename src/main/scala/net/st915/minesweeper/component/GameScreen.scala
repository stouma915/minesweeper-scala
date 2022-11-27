package net.st915.minesweeper.component

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.Coordinate
import net.st915.minesweeper.difficulty.Difficulty
import net.st915.minesweeper.event.*
import net.st915.minesweeper.implicits.*
import org.scalajs.dom.{Document, Element, HTMLDivElement, MouseEvent}

import scala.util.chaining.*

object GameScreen {

  private def onClick(implicit coord: Coordinate): IO[Unit] =
    for {
      event <- IO(CellClickEvent(coord))
      _ <- EventQueue.queue(event)
    } yield ()

  private def onRightClick(implicit coord: Coordinate): IO[Unit] =
    for {
      event <- IO(CellRightClickEvent(coord))
      _ <- EventQueue.queue(event)
    } yield ()

  def make(
      difficulty: Difficulty
  )(implicit doc: Document, runtime: IORuntime): IO[Element] = for {
    restartButton <- Button.make(
      "Restart",
      for {
        event <- IO(RestartButtonClickEvent())
        _ <- EventQueue.queue(event)
      } yield ()
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
                      implicit val _coord: Coordinate = Coordinate(x, y)

                      doc
                        .createElementWithType[HTMLDivElement]("div")
                        .tap { cellDiv =>
                          cellDiv.classList
                            .tap(_.add("cell"))
                            .tap(_.add("cellNotOpened"))
                        }
                        .tap(_.id = s"${x}_$y")
                        .tap(_.onclick = e => {
                          e.preventDefault()
                          onClick.unsafeRunAndForget()
                        })
                        .tap(_.oncontextmenu = e => {
                          e.preventDefault()
                          onRightClick.unsafeRunAndForget()
                        })
                        .tap(lineDiv.appendChild)
                    }
                  }
                  .tap(cellsDiv.appendChild)
              }
            }
            .tap(div.appendChild)
        }
        .tap(_.appendChild(doc.createElement("br")))
        .tap(_.appendChild(restartButton))
        .tap(_.appendChild(doc.createElement("br")))
    }
  } yield component

}
