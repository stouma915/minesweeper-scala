package net.st915.minesweeper.logic.eventloop

import cats.effect.Sync
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.logic.eventloop.task.Loop
import net.st915.minesweeper.{Difficulty, GameState}
import org.scalajs.dom.*

object EventLoop {

  private[eventloop] var gameState = GameState.empty

  def wired[F[_]: Sync](difficulty: Difficulty)(
    implicit document: HTMLDocument,
    window: Window,
    runtime: IORuntime
  ): F[Unit] = EventLoop(difficulty)

  def apply[F[_]: Sync](difficulty: Difficulty)(
    implicit document: HTMLDocument,
    window: Window,
    runtime: IORuntime
  ): F[Unit] = Loop.wired[F](difficulty)

}
