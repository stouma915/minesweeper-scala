package net.st915.minesweeper.ui.components

import cats.effect.Sync
import net.st915.minesweeper.ui.components.instances.*
import net.st915.minesweeper.ui.components.typeclasses.*
import net.st915.minesweeper.ui.consts.*
import net.st915.minesweeper.util.HTMLH1Element
import org.scalajs.dom.*

object InformationText {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](using HTMLDocument): F[HTMLDivElement] = {
    given CanAppendElement[F] = SyncCanAppendElement[F]
    given CanCreateElement[F, HTMLDivElement] = MonadCanCreateElementDiv[F]
    given CanCreateElement[F, HTMLH1Element] = MonadCanCreateElementH1[F]
    given CanUpdateElementClass[F] = SyncCanUpdateElementClass[F]
    given CanUpdateTextContent[F] = SyncCanUpdateTextContent[F]

    for {
      containerDiv <- CanCreateElement[F, HTMLDivElement].create
      _ <- CanUpdateElementClass[F].perform(containerDiv, CSSClasses.InformationText)

      innerText <- CanCreateElement[F, HTMLH1Element].create
      _ <- CanUpdateTextContent[F].perform(innerText, UITexts.CurrentlyUnderDevelopment)
      _ <- CanAppendElement[F].perform(containerDiv, innerText)
    } yield containerDiv
  }

}
