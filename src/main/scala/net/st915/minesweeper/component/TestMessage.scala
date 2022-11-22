package net.st915.minesweeper.component

import cats.effect.IO
import net.st915.minesweeper.Difficulty
import net.st915.minesweeper.implicits.*
import org.scalajs.dom.{Document, Element}

import scala.util.chaining.*

object TestMessage {

  def make(difficulty: Difficulty)(implicit doc: Document): IO[Element] = IO {
    doc
      .createElement("h1")
      .tap(_.appendChild("TEST.".textNode))
      .tap(_.appendChild(doc.createElement("br")))
      .tap(_.appendChild(s"Current difficulty is ${difficulty.name}.".textNode))
  }

}
