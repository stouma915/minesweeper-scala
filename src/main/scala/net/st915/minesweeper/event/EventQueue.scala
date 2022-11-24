package net.st915.minesweeper.event

import cats.effect.IO

object EventQueue {

  private var eventQueue = List[Event]()

  def queue(event: Event): IO[Unit] = IO {
    eventQueue = eventQueue.appended(event)
  }

  def nextEvent: IO[Option[Event]] = IO {
    eventQueue.headOption
  }

  def increment: IO[Unit] = IO {
    eventQueue = eventQueue.drop(1)
  }

  def all: IO[List[Event]] = IO(eventQueue)

}
