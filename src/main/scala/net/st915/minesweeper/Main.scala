package net.st915.minesweeper

import cats.effect.IO
import net.st915.minesweeper.implicits.*
import org.scalajs.dom.{Document, document}

import scala.util.chaining.*

@main def main(): Unit = {
  
  import cats.effect.unsafe.implicits.global

  implicit val _document: Document = document

  val printTestMessage = IO {
    println("TEST")
  }

  val appendTestMessage = IO {
    document.createElement("h1")
      .tap(_.appendChild("ABCD".textNode))
      .tap(document.body.appendChild)
  }

  val task = for {
    _ <- printTestMessage
    _ <- appendTestMessage
  } yield ()

  task.unsafeRunAndForget()

}
