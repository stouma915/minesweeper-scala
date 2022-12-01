package net.st915.minesweeper.event

import cats.effect.Sync

object EventQueue {

  private var eventQueue = List[Event]()

  def queue[F[_]: Sync](event: Event): F[Unit] = Sync[F].delay {
    eventQueue = eventQueue.appended(event)
  }

  def nextEvent[F[_]: Sync]: F[Option[Event]] = Sync[F].delay {
    eventQueue.headOption match {
      case Some(x) =>
        eventQueue = eventQueue.drop(1)
        Some(x)
      case None => None
    }
  }

}
