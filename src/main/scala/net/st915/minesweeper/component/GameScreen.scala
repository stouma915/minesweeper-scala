package net.st915.minesweeper.component

import cats.effect.IO
import net.st915.minesweeper.difficulty.Difficulty
import net.st915.minesweeper.implicits.*
import org.scalajs.dom.{Document, Element}

import scala.util.chaining.*

object GameScreen {

  def make(difficulty: Difficulty)(implicit doc: Document): IO[Element] = IO {
    doc
      .createElement("div")
      .tap(_.classList.add("gameScreen"))
      .tap { div =>
        (0 until difficulty.height).foreach { line =>
          doc
            .createElement("div")
            .tap(_.classList.add("line"))
            .tap { lineDiv =>
              (0 until difficulty.width).foreach { cell =>
                doc
                  .createElement("div")
                  .tap(_.classList.add("cellNotOpened"))
                  .tap(_.id = s"${line}_$cell")
                  .tap(lineDiv.appendChild)
              }
            }
            .tap(div.appendChild)
        }
      }
  }

}
