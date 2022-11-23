package net.st915.minesweeper.component

import cats.effect.IO
import net.st915.minesweeper.Difficulty
import net.st915.minesweeper.implicits.*
import org.scalajs.dom.{Document, Element}

import scala.util.chaining.*

object GameScreen {

  def make(difficulty: Difficulty)(implicit doc: Document): IO[Element] = IO {
    doc
      .createElement("div")
  }

}
