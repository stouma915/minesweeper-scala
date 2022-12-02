package net.st915.minesweeper.logic.eventloop.application

import net.st915.minesweeper.event.Event

object GetEventFromQueue {

  def apply[F[_]: GetEventFromQueue]: GetEventFromQueue[F] = implicitly

}

trait GetEventFromQueue[F[_]] {

  def get: F[Option[Event]]

}
