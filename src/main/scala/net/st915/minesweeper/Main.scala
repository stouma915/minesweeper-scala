package net.st915.minesweeper

import cats.effect.IO
import net.st915.minesweeper.beforeui.GetDifficulty
import net.st915.minesweeper.ui.RenderUI
import org.scalajs.dom.*

@main def main(): Unit = {

  import cats.effect.unsafe.implicits.global

  implicit val _document: HTMLDocument = document
  implicit val _window: Window = window

  lazy val getDifficulty = GetDifficulty.wired[IO]
  lazy val renderUI = RenderUI.wired[IO]

  val program = for {
    diff <- getDifficulty
    _ <- renderUI
  } yield ()

  program.start.unsafeRunAndForget()

}
