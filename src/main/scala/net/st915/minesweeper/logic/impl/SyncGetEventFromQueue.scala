package net.st915.minesweeper.logic.impl

import cats.effect.Sync
import net.st915.minesweeper.event.{Event, EventQueue}
import net.st915.minesweeper.logic.application.GetEventFromQueue

class SyncGetEventFromQueue[F[_]: Sync] extends GetEventFromQueue[F] {

  override def get: F[Option[Event]] = EventQueue.nextEvent[F]

}
