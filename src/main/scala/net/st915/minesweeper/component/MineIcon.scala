package net.st915.minesweeper.component

import cats.effect.IO
import net.st915.minesweeper.implicits.*
import org.scalajs.dom.{Document, Element}

import scala.util.chaining.*

object MineIcon {

  def make(implicit doc: Document): IO[Element] = IO {
    doc
      .createElement("div")
      .tap(_.classList.add("mine"))
      .tap { div =>
        doc
          .createElement("div")
          .tap { part =>
            part.classList
              .tap(_.add("minePart"))
              .tap(_.add("mineTop"))
          }
          .tap(div.appendChild)
      }
      .tap { div =>
        doc
          .createElement("div")
          .tap { part =>
            part.classList
              .tap(_.add("minePart"))
              .tap(_.add("mineMiddleLeft"))
          }
          .tap(div.appendChild)
      }
      .tap { div =>
        doc
          .createElement("div")
          .tap { part =>
            part.classList
              .tap(_.add("minePart"))
              .tap(_.add("mineMiddleCenter"))
          }
          .tap(div.appendChild)
      }
  }

}
