package net.st915.minesweeper

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.beforeui.BeforeUI
import net.st915.minesweeper.logic.eventloop.EventLoop
import net.st915.minesweeper.ui.RenderUI
import org.scalajs.dom.*

@main def main(): Unit = {

  implicit val _document: HTMLDocument = org.scalajs.dom.document
  implicit val _window: Window = org.scalajs.dom.window
  implicit val _runtime: IORuntime = cats.effect.unsafe.implicits.global

  val program = for {
    context <- BeforeUI.wired[IO]
    _ <- RenderUI.wired[IO](context.difficulty)
    _ <- EventLoop.wired[IO]
  } yield ()

  program.start.unsafeRunAndForget()

}
