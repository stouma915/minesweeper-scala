package net.st915.minesweeper.eventloop.tasks.typeclasses

import scala.concurrent.duration.FiniteDuration

object CanSleep {

  def apply[F[_]](using ev: CanSleep[F]): CanSleep[F] = ev

}

trait CanSleep[F[_]] {

  def perform(duration: FiniteDuration): F[Unit]

}
