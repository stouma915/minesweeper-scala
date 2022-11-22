package net.st915.minesweeper.component

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.implicits.*
import org.scalajs.dom.{Document, Element, Window}

import scala.util.chaining.*

object UnknownDifficulty {

  def make(implicit
      doc: Document,
      wind: Window,
      runtime: IORuntime
  ): IO[Element] = for {
    diffSelector <- DifficultySelector.make
    unknownDiff <- IO {
      doc
        .createElement("div")
        .tap { div =>
          doc
            .createElement("p")
            .tap(_.appendChild("Unknown Difficulty.".textNode))
            .tap(_.appendChild(diffSelector))
            .tap(div.appendChild)
        }
    }
  } yield unknownDiff

}
