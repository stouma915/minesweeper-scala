package net.st915.minesweeper.component

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.Coordinate
import net.st915.minesweeper.difficulty.Difficulty
import net.st915.minesweeper.implicits.*
import org.scalajs.dom.{Document, Element, HTMLDivElement, MouseEvent}

import scala.util.chaining.*

object GameScreen {

  def onClick(implicit event: MouseEvent, coord: Coordinate): IO[Unit] =
    IO {
      println(s"CLICK: (${coord.x}, ${coord.y})")
    }

  def onRightClick(implicit event: MouseEvent, coord: Coordinate): IO[Unit] =
    IO {
      println(s"RIGHT CLICK: (${coord.x}, ${coord.y})")
    }

  def make(
      difficulty: Difficulty
  )(implicit doc: Document, runtime: IORuntime): IO[Element] = IO {
    doc
      .createElement("div")
      .tap(_.classList.add("gameScreen"))
      .tap { div =>
        (0 until difficulty.height).foreach { y =>
          doc
            .createElement("div")
            .tap(_.classList.add("line"))
            .tap { lineDiv =>
              (0 until difficulty.width).foreach { x =>
                implicit val _coord: Coordinate = Coordinate(x, y)

                doc
                  .createElement("div")
                  .asInstanceOf[HTMLDivElement]
                  .tap(_.classList.add("cellNotOpened"))
                  .tap(_.id = s"${x}_$y")
                  .tap(_.onclick = implicit e => {
                    e.preventDefault()
                    onClick.unsafeRunAndForget()
                  })
                  .tap(_.oncontextmenu = implicit e => {
                    e.preventDefault()
                    onRightClick.unsafeRunAndForget()
                  })
                  .tap(lineDiv.appendChild)
              }
            }
            .tap(div.appendChild)
        }
      }
  }

}
