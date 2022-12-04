package net.st915.minesweeper.ui.component

import cats.effect.unsafe.IORuntime
import cats.effect.{IO, Sync}
import net.st915.minesweeper.Consts.{ElementID, Text}
import net.st915.minesweeper.EventQueue
import net.st915.minesweeper.event.ButtonClickEvent
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.impl.*
import org.scalajs.dom.*

object ToggleFlagModeButton {

  def wired[F[_]: Sync](
    implicit document: HTMLDocument,
    runtime: IORuntime
  ): F[HTMLDivElement] = {
    implicit val _appendElement: AppendElement[F] = SyncAppendElement[F]
    implicit val _createTextNode: CreateTextNode[F] = SyncCreateTextNode[F]
    implicit val _appendTextNode: AppendTextNode[F] = SyncAppendTextNode[F]
    implicit val _createElement: CreateElement[F] = SyncCreateElement[F]
    implicit val _createDiv: CreateDiv[F] = SyncCreateDiv[F]
    implicit val _createSpan: CreateSpan[F] = SyncCreateSpan[F]
    implicit val _updateHTMLClass: UpdateHTMLClass[F] = SyncUpdateHTMLClass[F]
    implicit val _updateElementID: UpdateElementID[F] = SyncUpdateElementID[F]
    implicit val _updateElementClickEvent: UpdateElementClickEvent[F] =
      SyncUpdateElementClickEvent[F]
    implicit val _updateElementRightClickEvent: UpdateElementRightClickEvent[F] =
      SyncUpdateElementRightClickEvent[F]
    implicit val _createButton: CreateButton[F] = SyncCreateButton[F]

    ToggleFlagModeButton()
  }

  def apply[F[_]: Sync: CreateButton]()(
    implicit document: HTMLDocument,
    runtime: IORuntime
  ): F[HTMLDivElement] =
    CreateButton[F].create(
      Text.EnterFlagPlaceMode,
      ElementID.ToggleFlagModeButtonId,
      EventQueue.queue[IO](ButtonClickEvent(ElementID.ToggleFlagModeButtonId))
    )

}
