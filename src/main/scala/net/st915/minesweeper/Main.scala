package net.st915.minesweeper

import cats.effect.IO
import net.st915.minesweeper.ui.RenderUI
import org.scalajs.dom.*

@main def main(): Unit = {

  import cats.effect.unsafe.implicits.global
  import cats.syntax.traverse.*

  implicit val _document: HTMLDocument = document
  implicit val _window: Window = window

  val renderUI = RenderUI.wired[IO]

  val program = for {
    _ <- renderUI
  } yield ()

  program.start.unsafeRunAndForget()

}
