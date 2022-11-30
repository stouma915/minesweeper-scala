package net.st915.minesweeper.component

import net.st915.minesweeper.implicits.*
import org.scalajs.dom.{Element, HTMLDocument}

import scala.util.chaining.*

object InformationText {

  def make(implicit document: HTMLDocument): Element =
    document
      .createElement("div")
      .tap(_.classList.add("informationTextContainer"))
      .tap { div =>
        document
          .createElement("h1")
          .tap(_.textContent = "Currently Under Development.")
          .tap(div.appendChild)
      }

}
