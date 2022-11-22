package net.st915.minesweeper

import cats.effect.IO
import net.st915.minesweeper.component.*
import org.scalajs.dom.{
  Document,
  Element,
  Window,
  document,
  window
}

@main def main(): Unit = {
  
  import cats.effect.unsafe.implicits.global

  implicit val _document: Document = document
  implicit val _window: Window = window

  def appendToBody(e: Element): IO[Unit] = IO {
    document.body.appendChild(e)
  }

  val task = for {
    testMessage <- TestMessage.make
    aboutPage <- AboutPage.make
    unknownDiff <- UnknownDifficulty.make
    _ <- appendToBody(testMessage)
    _ <- appendToBody(unknownDiff)
    _ <- appendToBody(aboutPage)
  } yield ()

  task.unsafeRunAndForget()

}
