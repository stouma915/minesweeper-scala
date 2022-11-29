package net.st915.minesweeper.component

import cats.effect.IO
import net.st915.minesweeper.implicits.*
import org.scalajs.dom.*

import scala.util.chaining.*

object MineCountContainer {

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

  def textColor(n: Int): String =
    n match {
      case 1 => "#0200f9"
      case 2 => "#008001"
      case 3 => "#fd0100"
      case 4 => "#010080"
      case 5 => "#800002"
      case 6 => "#00817c"
      case 7 => "#000000"
      case 8 => "#808080"
      case _ => "transparent"
    }

  def make(id: String, num: Int)(implicit doc: Document): IO[Element] =
    IO {
      doc
        .createElementWithType[HTMLDivElement]("div")
        .tap(_.classList.add("mineCountContainer"))
        .tap(_.textContent = num.toString)
        .tap(_.id = id)
        .tap(_.style.color = textColor(num))
    }

}
