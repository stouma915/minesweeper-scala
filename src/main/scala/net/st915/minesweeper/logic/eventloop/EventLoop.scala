package net.st915.minesweeper.logic.eventloop

import cats.effect.Sync
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.GameState
import net.st915.minesweeper.logic.eventloop.task.Loop
import org.scalajs.dom.*

object EventLoop {

  private[eventloop] var gameState = GameState.empty

  def wired[F[_]: Sync](
    implicit document: HTMLDocument,
    window: Window,
    runtime: IORuntime
  ): F[Unit] = EventLoop()

  def apply[F[_]: Sync]()(
    implicit document: HTMLDocument,
    window: Window,
    runtime: IORuntime
  ): F[Unit] = Loop.wired[F]

}
