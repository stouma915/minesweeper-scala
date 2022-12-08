package net.st915.minesweeper

import cats.effect.Sync
import net.st915.minesweeper.event.Event

object EventQueue {

  private var eventQueue = List[Event]()

  def queue[F[_]: Sync](event: Event): F[Unit] = Sync[F].blocking {
    eventQueue = eventQueue.appended(event)
  }

  def nextEvent[F[_]: Sync]: F[Option[Event]] = Sync[F].blocking {
    eventQueue.headOption match
      case Some(x) =>
        eventQueue = eventQueue.drop(1)
        Some(x)
      case None => None
  }

}
