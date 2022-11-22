package net.st915.minesweeper

import cats.effect.IO
import net.st915.minesweeper.component.*
import org.scalajs.dom.{Document, Element, document}

@main def main(): Unit = {
  
  import cats.effect.unsafe.implicits.global

  implicit val _document: Document = document

  def appendToBody(e: Element): IO[Unit] = IO {
    document.body.appendChild(e)
  }

  val task = for {
    testMessage <- TestMessage.make
    aboutPage <- AboutPage.make
    _ <- appendToBody(testMessage)
    _ <- appendToBody(aboutPage)
  } yield ()

  task.unsafeRunAndForget()

}
