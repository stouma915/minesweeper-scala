package net.st915.minesweeper.logic.impl

import cats.effect.Sync
import net.st915.minesweeper.event.*
import net.st915.minesweeper.logic.application.EventDistinction

class SyncEventDistinction[F[_]: Sync] extends EventDistinction[F] {

  override def perform(maybeEvent: Option[Event]): F[Unit] =
    maybeEvent match {
      case Some(event) =>
        event match
          case ButtonClickEvent(buttonId) =>
            Sync[F].pure {
              println(s"Button Clicked: $buttonId")
            }
          case _ => Sync[F].unit
      case None => Sync[F].unit
    }

}
