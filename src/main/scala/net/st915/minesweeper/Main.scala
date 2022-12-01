package net.st915.minesweeper

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.beforeui.GetDifficulty
import net.st915.minesweeper.logic.EventLoop
import net.st915.minesweeper.ui.RenderUI
import org.scalajs.dom.*

@main def main(): Unit = {

  implicit val _document: HTMLDocument = document
  implicit val _window: Window = window
  implicit val _runtime: IORuntime = cats.effect.unsafe.implicits.global

  val program = for {
    diff <- GetDifficulty.wired[IO]
    _ <- RenderUI.wired[IO](diff)
    _ <- EventLoop.wired[IO]
  } yield ()

  program.start.unsafeRunAndForget()

}
