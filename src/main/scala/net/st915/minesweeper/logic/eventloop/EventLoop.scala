package net.st915.minesweeper.logic.eventloop

import cats.effect.Sync
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.GameState
import net.st915.minesweeper.logic.eventhandler.application.*
import net.st915.minesweeper.logic.eventhandler.impl.*
import net.st915.minesweeper.logic.eventloop.application.*
import net.st915.minesweeper.logic.eventloop.impl.*
import net.st915.minesweeper.logic.refreshui.application.*
import net.st915.minesweeper.logic.refreshui.impl.*
import net.st915.minesweeper.logic.refreshui.task.application.*
import net.st915.minesweeper.logic.refreshui.task.impl.*
import org.scalajs.dom.*

object EventLoop {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*
  import cats.syntax.traverse.*

  private var gameState = GameState.empty

  def wired[F[_]: Sync](
    implicit document: HTMLDocument,
    window: Window,
    runtime: IORuntime
  ): F[Unit] = {
    // format: off
    implicit val _handleButtonClickEvent: HandleButtonClickEvent[F] = SyncHandleButtonClickEvent[F]
    implicit val _handleCellClickEvent: HandleCellClickEvent[F] = SyncHandleCellClickEvent[F]
    implicit val _handleCellRightClickEvent: HandleCellRightClickEvent[F] = SyncHandleCellRightClickEvent[F]

    implicit val _eventDistinction: EventDistinction[F] = SyncEventDistinction[F]
    implicit val _getEventFromQueue: GetEventFromQueue[F] = SyncGetEventFromQueue[F]
    implicit val _loop: Loop[F] = SyncLoop[F]

    implicit val _getElement: GetElement[F] = SyncGetElement[F]
    implicit val _updateTextContent: UpdateTextContent[F] = SyncUpdateTextContent[F]

    implicit val _updateButtonText: UpdateButtonText[F] = SyncUpdateButtonText[F]
    implicit val _refreshUI: RefreshUI[F] = SyncRefreshUI[F]
    // format: on

    EventLoop()
  }

  def apply[F[_]: Sync: EventDistinction: GetEventFromQueue: RefreshUI: Loop]()(
    implicit document: HTMLDocument,
    window: Window,
    runtime: IORuntime
  ): F[Unit] =
    Loop[F].perform {
      for {
        maybeEvent <- GetEventFromQueue[F].get
        _ <-
          maybeEvent match
            case Some(event) =>
              for {
                newState <- EventDistinction[F].perform(event, gameState)
                _ <- RefreshUI[F].perform(newState)
                _ <- Sync[F].delay(this.gameState = newState)
              } yield ()
            case None => Sync[F].unit
      } yield ()
    }

}
