package net.st915.minesweeper.ui.components

import cats.effect.Sync
import net.st915.minesweeper.ui.components.typeclasses.*
import net.st915.minesweeper.ui.components.instances.*
import net.st915.minesweeper.ui.consts.*
import org.scalajs.dom.*

object DifficultySelector {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](using HTMLDocument, Window): F[HTMLDivElement] = {
    given CanAppendElement[F] = SyncCanAppendElement[F]
    given CanCreateElement[F, HTMLDivElement] = MonadCanCreateElementDiv[F]
    given CanCreateElement[F, HTMLSpanElement] = MonadCanCreateElementSpan[F]
    given CanUpdateElementClass[F] = SyncCanUpdateElementClass[F]
    given CanUpdateTextContent[F] = SyncCanUpdateTextContent[F]

    for {
      containerDiv <- CanCreateElement[F, HTMLDivElement].create
      _ <- CanUpdateElementClass[F].perform(containerDiv, CSSClass.DifficultySelector)

      selectorText <- CanCreateElement[F, HTMLSpanElement].create
      _ <- CanUpdateTextContent[F].perform(selectorText, UIText.DifficultiesColon)
      _ <- CanAppendElement[F].perform(containerDiv, selectorText)

      diffLinks <- DifficultyLinks.wired[F]
      _ <- CanAppendElement[F].perform(containerDiv, diffLinks)
    } yield containerDiv
  }

}
