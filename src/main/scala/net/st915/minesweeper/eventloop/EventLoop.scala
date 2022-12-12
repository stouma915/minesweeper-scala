package net.st915.minesweeper.eventloop

import cats.effect.Async
import net.st915.minesweeper.{Consts, GameState}
import net.st915.minesweeper.eventloop.tasks.*
import net.st915.minesweeper.eventloop.tasks.instances.*
import net.st915.minesweeper.eventloop.tasks.typeclasses.*
import org.scalajs.dom.*

object EventLoop {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Async](gameState: GameState)(using Window): F[Unit] = {
    given CanSleep[F] = AsyncCanSleep[F]

    for {
      newState <- Routine.wired[F](gameState)
      _ <- CanSleep[F].perform(Consts.EventLoopInterval)
      _ <- EventLoop.wired[F](newState)
    } yield ()
  }

}
