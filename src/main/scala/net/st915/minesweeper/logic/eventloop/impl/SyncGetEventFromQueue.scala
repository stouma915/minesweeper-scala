package net.st915.minesweeper.logic.eventloop.impl

import cats.effect.Sync
import net.st915.minesweeper.EventQueue
import net.st915.minesweeper.event.Event
import net.st915.minesweeper.logic.eventloop.application.GetEventFromQueue

class SyncGetEventFromQueue[F[_]: Sync] extends GetEventFromQueue[F] {

  override def get: F[Option[Event]] = EventQueue.nextEvent[F]

}
