package net.st915.minesweeper.component

import cats.effect.IO
import org.scalajs.dom.*

import scala.util.chaining.*

object RenderingScreen {

  def make(implicit doc: Document): IO[Element] = IO {
    doc
      .createElement("div")
      .tap { div =>
        doc
          .createElement("h2")
          .tap(_.textContent = "Rendering...")
          .tap(div.appendChild)
      }
  }

}
