package net.st915.minesweeper.component

import cats.effect.IO
import net.st915.minesweeper.implicits.*
import org.scalajs.dom.*

import scala.util.chaining.*

object IconContainer {

  def updateVisibility(
      id: String,
      visibility: Boolean
  )(implicit doc: Document): IO[Unit] = IO {
    doc
      .getElementByIdWithType[HTMLDivElement](id)
      .tap { div =>
        if (visibility) {
          if (div.style.display != "block")
            div.style.display = "block"
        } else {
          if (div.style.display != "none")
            div.style.display = "none"
        }
      }
  }

  def make(id: String, icon: Element)(implicit doc: Document): IO[Element] =
    IO {
      doc
        .createElementWithType[HTMLDivElement]("div")
        .tap(_.classList.add("iconContainer"))
        .tap(_.id = id)
        .tap(_.appendChild(icon))
    }

}
