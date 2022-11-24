package net.st915.minesweeper.event

import cats.effect.IO

object EventQueue {

  private var eventQueue = List[Event]()

  def queue(event: Event): IO[Unit] = IO {
    eventQueue = eventQueue.appended(event)
  }

  def nextEvent: IO[Option[Event]] = IO {
    eventQueue.headOption match
      case Some(x) =>
        eventQueue = eventQueue.drop(1)
        Some(x)
      case None => None
  }

}
