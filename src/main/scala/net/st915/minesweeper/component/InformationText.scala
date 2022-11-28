package net.st915.minesweeper.component

import cats.effect.IO
import net.st915.minesweeper.implicits.*
import org.scalajs.dom.{Document, Element, HTMLLinkElement}

import scala.util.chaining.*

object InformationText {

  def make(implicit doc: Document): IO[Element] = IO {
    doc
      .createElement("div")
      .tap { div =>
        doc
          .createElement("h1")
          .tap(_.appendChild("Currently Under Development.".textNode))
          .tap(div.appendChild)
      }
  }

}
