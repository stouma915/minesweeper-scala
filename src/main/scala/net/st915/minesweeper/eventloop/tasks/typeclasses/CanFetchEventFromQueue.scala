package net.st915.minesweeper.eventloop.tasks.typeclasses

import net.st915.minesweeper.event.Event

object CanFetchEventFromQueue {

  def apply[F[_]](using ev: CanFetchEventFromQueue[F]): CanFetchEventFromQueue[F] = ev

}

trait CanFetchEventFromQueue[F[_]] {

  def fetch: F[Option[Event]]

}
