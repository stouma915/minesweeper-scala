package net.st915.minesweeper.component

import cats.effect.IO
import net.st915.minesweeper.implicits.*
import org.scalajs.dom.{Document, Element}

import scala.util.chaining.*

object TestMessage {

  def make(implicit doc: Document): IO[Element] = IO {
    doc.createElement("h1")
      .tap(_.appendChild("ABCD".textNode))
  }

}
