package net.st915.minesweeper.ui.component

import cats.effect.Sync
import net.st915.minesweeper.Consts.{CSSClass, Text}
import net.st915.minesweeper.Difficulties
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.impl.*
import org.scalajs.dom.*

object DifficultySelector {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*
  import cats.syntax.traverse.*

  def wired[F[_]: Sync](implicit document: HTMLDocument, window: Window): F[HTMLDivElement] = {
    implicit val _createElement: CreateElement[F] = SyncCreateElement[F]
    implicit val _appendElement: AppendElement[F] = SyncAppendElement[F]
    implicit val _createBR: CreateBR[F] = SyncCreateBR[F]
    implicit val _appendBR: AppendBR[F] = SyncAppendBR[F]
    implicit val _createTextNode: CreateTextNode[F] = SyncCreateTextNode[F]
    implicit val _appendTextNode: AppendTextNode[F] = SyncAppendTextNode[F]
    implicit val _createDifficultyLink: CreateDifficultyLink[F] = ApplicativeCreateDifficultyLink[F]
    implicit val _createDiv: CreateDiv[F] = SyncCreateDiv[F]
    implicit val _createLink: CreateLink[F] = SyncCreateLink[F]
    implicit val _createSpan: CreateSpan[F] = SyncCreateSpan[F]
    implicit val _updateHTMLClass: UpdateHTMLClass[F] = SyncUpdateHTMLClass[F]
    implicit val _updateHyperlink: UpdateHyperlink[F] = SyncUpdateHyperlink[F]

    DifficultySelector()
  }

  def apply[
    F[
      _
    ]: Sync: AppendBR: AppendElement: AppendTextNode: CreateDifficultyLink: CreateDiv: CreateLink: CreateSpan: UpdateHTMLClass: UpdateHyperlink
  ]()(implicit document: HTMLDocument, window: Window): F[HTMLDivElement] =
    for {
      containerDiv <- CreateDiv[F].create
      _ <- UpdateHTMLClass[F].update(containerDiv, CSSClass.DifficultySelector)
      span <- CreateSpan[F].create
      _ <- AppendTextNode[F].append(span, Text.DifficultiesColon)
      _ <- AppendBR[F].append(span)
      _ <- AppendElement[F].append(containerDiv, span)
      links <- Difficulties.All.map { diff =>
        for {
          link <- CreateLink[F].create
          _ <- AppendTextNode[F].append(link, diff.displayName)
          _ <- AppendBR[F].append(link)
          hyperlink <- CreateDifficultyLink[F].create(diff)
          _ <- UpdateHyperlink[F].update(link, hyperlink)
        } yield link
      }.sequence
      _ <- links
        .map(link => AppendElement[F].append(containerDiv, link))
        .sequence
    } yield containerDiv

}
