package net.st915.minesweeper

import cats.effect.*
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.beforeui.BeforeUI
import net.st915.minesweeper.eventloop.EventLoop
import net.st915.minesweeper.ui.RenderUI
import org.scalajs.dom.*

object Minesweeper extends IOApp {

  given HTMLDocument = org.scalajs.dom.document
  given Window = org.scalajs.dom.window
  given IORuntime = cats.effect.unsafe.implicits.global

  def run(args: List[String]): IO[ExitCode] = {
    val initialGameState = GameState.empty

    val program = for {
      given RunContext <- BeforeUI.wired[IO]
      _ <- RenderUI.wired[IO]
      _ <- EventLoop.wired[IO](initialGameState)
    } yield ()

    program.start >> IO(ExitCode.Success)
  }

}
