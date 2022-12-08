package net.st915.minesweeper.eventloop.tasks.instances

import cats.effect.Sync
import net.st915.minesweeper.EventQueue
import net.st915.minesweeper.event.Event
import net.st915.minesweeper.eventloop.tasks.typeclasses.CanFetchEventFromQueue

class SyncCanFetchEventFromQueue[F[_]: Sync] extends CanFetchEventFromQueue[F] {

  override def fetch: F[Option[Event]] = EventQueue.nextEvent[F]

}
