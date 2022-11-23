package net.st915.minesweeper.component

import cats.effect.IO
import net.st915.minesweeper.implicits.*
import org.scalajs.dom.{Document, Element, HTMLLinkElement}

import scala.util.chaining.*

object AboutPage {

  def make(implicit doc: Document): IO[Element] = IO {
    doc
      .createElement("div")
      .tap { div =>
        doc
          .createElement("p")
          .tap(_.appendChild("This site is licensed under the ".textNode))
          .tap { p =>
            doc
              .createElementWithType[HTMLLinkElement]("a")
              .tap(_.appendChild("MIT License".textNode))
              .tap(_.href =
                "https://github.com/stouma915/minesweeper-scala/blob/main/LICENSE"
              )
              .tap(p.appendChild)
          }
          .tap(_.appendChild(".".textNode))
          .tap(_.appendChild(doc.createElement("br")))
          .tap(_.appendChild("This site is open source. ".textNode))
          .tap { p =>
            doc
              .createElementWithType[HTMLLinkElement]("a")
              .tap(_.appendChild("Improve this site".textNode))
              .tap(_.href = "https://github.com/stouma915/minesweeper-scala")
              .tap(p.appendChild)
          }
          .tap(_.appendChild(".".textNode))
          .tap(_.appendChild(doc.createElement("br")))
          .tap(_.appendChild("Powered by ".textNode))
          .tap { p =>
            doc
              .createElementWithType[HTMLLinkElement]("a")
              .tap(_.appendChild("GitHub Pages".textNode))
              .tap(_.href = "https://pages.github.com")
              .tap(p.appendChild)
          }
          .tap(_.appendChild(".".textNode))
          .tap(div.appendChild)
      }
  }

}
