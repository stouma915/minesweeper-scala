package net.st915.minesweeper.ui.components.instances

import cats.effect.Sync
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.EventQueue
import net.st915.minesweeper.event.ButtonClickEvent
import net.st915.minesweeper.ui.components.instances.*
import net.st915.minesweeper.ui.components.typeclasses.*
import net.st915.minesweeper.ui.consts.CSSClasses
import net.st915.minesweeper.util.{ID, UIText}
import org.scalajs.dom.*

class SyncCanCreateButton[F[_]: Sync] extends CanCreateButton[F] {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  override def create(id: ID, text: UIText)(using HTMLDocument, IORuntime): F[HTMLDivElement] = {
    given CanAppendElement[F] = SyncCanAppendElement[F]
    given CanCreateElement[F, HTMLDivElement] = MonadCanCreateElementDiv[F]
    given CanCreateElement[F, HTMLSpanElement] = MonadCanCreateElementSpan[F]
    given CanUpdateElementClass[F] = SyncCanUpdateElementClass[F]
    given CanUpdateElementID[F] = SyncCanUpdateElementID[F]
    given CanUpdateClickEvent[F] = SyncCanUpdateClickEvent[F]
    given CanUpdateRightClickEvent[F] = SyncCanUpdateRightClickEvent[F]
    given CanUpdateTextContent[F] = SyncCanUpdateTextContent[F]

    for {
      containerDiv <- CanCreateElement[F, HTMLDivElement].create
      _ <- CanUpdateElementClass[F].perform(containerDiv, CSSClasses.Button)

      innerText <- CanCreateElement[F, HTMLSpanElement].create
      _ <- CanUpdateTextContent[F].perform(innerText, text)
      _ <- CanUpdateElementClass[F].perform(innerText, CSSClasses.ButtonInner)
      _ <- CanUpdateElementID[F].perform(innerText, id)
      _ <- CanUpdateClickEvent[F].perform(
        innerText,
        EventQueue.queue[F](ButtonClickEvent(id))
      )
      _ <- CanUpdateRightClickEvent[F].perform(
        innerText,
        Sync[F].unit
      )
      _ <- CanAppendElement[F].perform(containerDiv, innerText)
    } yield containerDiv
  }

}
