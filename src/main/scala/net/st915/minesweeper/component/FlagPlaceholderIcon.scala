package net.st915.minesweeper.component

import cats.effect.IO
import net.st915.minesweeper.implicits.*
import org.scalajs.dom.{Document, Element}

import scala.util.chaining.*

object FlagPlaceholderIcon {

  def make(implicit doc: Document): IO[Element] = IO {
    doc
      .createElement("div")
      .tap(_.classList.add("flagPlaceholder"))
      .tap { div =>
        doc
          .createElement("div")
          .tap { part =>
            part.classList
              .tap(_.add("flagPlaceholderPart"))
              .tap(_.add("flagPlaceholderTop"))
          }
          .tap(div.appendChild)
      }
      .tap { div =>
        doc
          .createElement("div")
          .tap { part =>
            part.classList
              .tap(_.add("flagPlaceholderPart"))
              .tap(_.add("flagPlaceholderMiddle"))
          }
          .tap(div.appendChild)
      }
      .tap { div =>
        doc
          .createElement("div")
          .tap { part =>
            part.classList
              .tap(_.add("flagPlaceholderPart"))
              .tap(_.add("flagPlaceholderBottom"))
          }
          .tap(div.appendChild)
      }
  }

}
