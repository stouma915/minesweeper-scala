package net.st915.minesweeper.component

import cats.effect.IO
import net.st915.minesweeper.implicits.*
import org.scalajs.dom.*

import scala.util.chaining.*

object FlagIcon {

  def make(implicit doc: Document): IO[Element] = IO {
    doc
      .createElement("div")
      .tap(_.classList.add("flag"))
      .tap { div =>
        doc
          .createElement("div")
          .tap { part =>
            part.classList
              .tap(_.add("flagPart"))
              .tap(_.add("flagTop"))
          }
          .tap(div.appendChild)
      }
      .tap { div =>
        doc
          .createElement("div")
          .tap { part =>
            part.classList
              .tap(_.add("flagPart"))
              .tap(_.add("flagMiddle"))
          }
          .tap(div.appendChild)
      }
      .tap { div =>
        doc
          .createElement("div")
          .tap { part =>
            part.classList
              .tap(_.add("flagPart"))
              .tap(_.add("flagBottom"))
          }
          .tap(div.appendChild)
      }
  }

}
