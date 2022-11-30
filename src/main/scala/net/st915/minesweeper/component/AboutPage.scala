package net.st915.minesweeper.component

import net.st915.minesweeper.implicits.*
import org.scalajs.dom.{Element, HTMLDocument, HTMLLinkElement}

import scala.util.chaining.*

object AboutPage {

  def make(implicit document: HTMLDocument): Element =
    document
      .createElement("div")
      .tap(_.classList.add("aboutPageContainer"))
      .tap { div =>
        document
          .createElement("p")
          .tap(_.appendChild("This site is licensed under the ".textNode))
          .tap { p =>
            document
              .createElementWithType[HTMLLinkElement]("a")
              .tap(_.appendChild("MIT License".textNode))
              .tap(_.href =
                "https://github.com/stouma915/minesweeper-scala/blob/main/LICENSE"
              )
              .tap(p.appendChild)
          }
          .tap(_.appendChild(".".textNode))
          .tap(_.appendChild(document.makeBR))
          .tap(_.appendChild("This site is open source. ".textNode))
          .tap { p =>
            document
              .createElementWithType[HTMLLinkElement]("a")
              .tap(_.appendChild("Improve this site".textNode))
              .tap(_.href = "https://github.com/stouma915/minesweeper-scala")
              .tap(p.appendChild)
          }
          .tap(_.appendChild(".".textNode))
          .tap(_.appendChild(document.makeBR))
          .tap(_.appendChild("Powered by ".textNode))
          .tap { p =>
            document
              .createElementWithType[HTMLLinkElement]("a")
              .tap(_.appendChild("GitHub Pages".textNode))
              .tap(_.href = "https://pages.github.com")
              .tap(p.appendChild)
          }
          .tap(_.appendChild(".".textNode))
          .tap(div.appendChild)
      }

}
