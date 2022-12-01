package net.st915.minesweeper.logic.application

import net.st915.minesweeper.event.Event

object EventDistinction {

  def apply[F[_]: EventDistinction]: EventDistinction[F] = implicitly

}

trait EventDistinction[F[_]] {

  def perform(maybeEvent: Option[Event]): F[Unit]

}
