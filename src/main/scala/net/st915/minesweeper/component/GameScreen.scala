package net.st915.minesweeper.component

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.difficulty.Difficulty
import net.st915.minesweeper.event.*
import net.st915.minesweeper.implicits.*
import net.st915.minesweeper.{Constants, Coordinate}
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

  def updateCellClassName(coord: Coordinate, newClassName: String)(implicit
      doc: Document
  ): IO[Unit] =
    IO {
      doc
        .getElementByIdWithType[HTMLDivElement](s"${coord.x}_${coord.y}")
        .tap { cellElem =>
          if (cellElem.className != newClassName)
            cellElem.className = newClassName
        }
    }

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
                        .tap { cellDiv =>
                          val program = for {
                            flagIcon <- FlagIcon.make
                            flagPlaceholderIcon <- FlagPlaceholderIcon.make
                            mineIcon <- MineIcon.make
                            flagContainer <- IconContainer.make(
                              s"flagContainer_${x}_$y",
                              flagIcon
                            )
                            flagPlaceholderContainer <- IconContainer.make(
                              s"flagPlaceholderContainer_${x}_$y",
                              flagPlaceholderIcon
                            )
                            mineContainer <- IconContainer.make(
                              s"mineContainer_${x}_$y",
                              mineIcon
                            )
                            _ <- IO {
                              cellDiv
                                .tap(_.appendChild(flagContainer))
                                .tap(_.appendChild(flagPlaceholderContainer))
                                .tap(_.appendChild(mineContainer))
                            }
                          } yield ()

                          program.unsafeRunAndForget()
                        }
                        .tap(lineDiv.appendChild)
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
