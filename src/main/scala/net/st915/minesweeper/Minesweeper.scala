package net.st915.minesweeper

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.beforeui.BeforeUI
import net.st915.minesweeper.eventloop.EventLoop
import net.st915.minesweeper.ui.RenderUI
import org.scalajs.dom.*

@main def main(): Unit = {

  given HTMLDocument = org.scalajs.dom.document
  given Window = org.scalajs.dom.window
  given IORuntime = cats.effect.unsafe.implicits.global

  val program = for {
    runContext <- BeforeUI.wired[IO]
    _ <- RenderUI.wired[IO](runContext)
    _ <- EventLoop.wired[IO](GameState.empty)
  } yield ()

  program.start.unsafeRunAndForget()

}
