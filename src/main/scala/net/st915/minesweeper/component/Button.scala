package net.st915.minesweeper.component

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.implicits.*
import org.scalajs.dom.{Document, Element, HTMLSpanElement}

import scala.util.chaining.*

object Button {

  def make(
    text: String,
    onClick: IO[Unit]
  )(implicit doc: Document, runtime: IORuntime): IO[Element] = IO {
    doc
      .createElement("div")
      .tap(_.classList.add("btn"))
      .tap { div =>
        doc
          .createElementWithType[HTMLSpanElement]("span")
          .tap(_.classList.add("btnText"))
          .tap(_.appendChild(text.textNode))
          .tap(_.onclick = e => {
            e.preventDefault()
            onClick.unsafeRunAndForget()
          })
          .tap(div.appendChild)
      }
  }

}
