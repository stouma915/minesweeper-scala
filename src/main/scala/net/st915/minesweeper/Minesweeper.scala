package net.st915.minesweeper

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.beforeui.BeforeUI
import net.st915.minesweeper.logic.eventloop.EventLoop
import net.st915.minesweeper.ui.{LegacyRenderUI, RenderUI}
import org.scalajs.dom.*

@main def main(): Unit = {

  implicit val _document: HTMLDocument = org.scalajs.dom.document
  implicit val _window: Window = org.scalajs.dom.window
  implicit val _runtime: IORuntime = cats.effect.unsafe.implicits.global

  val program = for {
    runContext <- BeforeUI.wired[IO]
    _ <- LegacyRenderUI.wired[IO](runContext.difficulty)
    //_ <- RenderUI.wired[IO](runContext)
    _ <- EventLoop.wired[IO](runContext.difficulty)
  } yield ()

  program.start.unsafeRunAndForget()

}
