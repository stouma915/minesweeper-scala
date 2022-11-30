package net.st915.minesweeper.component

import net.st915.minesweeper.Difficulties
import net.st915.minesweeper.implicits.*
import org.scalajs.dom.{Element, HTMLDocument, HTMLLinkElement, URL, Window}

import scala.util.chaining.*

object DifficultySelector {

  def make(implicit document: HTMLDocument, window: Window): Element =
    document
      .createElement("div")
      .tap(_.classList.add("difficultySelectorContainer"))
      .tap { div =>
        document
          .createElement("span")
          .tap(_.appendChild("Difficulties:".textNode))
          .tap(_.appendChild(document.makeBR))
          .tap(div.appendChild)
      }
      .tap { div =>
        Difficulties.All
          .map { diff =>
            document
              .createElementWithType[HTMLLinkElement]("a")
              .tap(_.appendChild(diff.displayName.textNode))
              .tap(_.appendChild(document.makeBR))
              .tap(_.href = {
                val currentURL = new URL(window.location.href)
                val param =
                  if (diff eq Difficulties.Default) "" else s"?d=${diff.id}"

                s"${currentURL.origin}${currentURL.pathname}$param"
              })
          }
          .map { link =>
            div.appendChild(link)
          }
      }

}
