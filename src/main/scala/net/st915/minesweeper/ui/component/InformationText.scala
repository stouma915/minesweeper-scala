package net.st915.minesweeper.ui.component

import cats.effect.Sync
import net.st915.minesweeper.Consts.{CSSClass, Text}
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.impl.*
import org.scalajs.dom.*

object InformationText {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](implicit document: HTMLDocument): F[HTMLDivElement] = {
    implicit val _appendElement: AppendElement[F] = SyncAppendElement[F]
    implicit val _createTextNode: CreateTextNode[F] = SyncCreateTextNode[F]
    implicit val _appendTextNode: AppendTextNode[F] = SyncAppendTextNode[F]
    implicit val _createElement: CreateElement[F] = SyncCreateElement[F]
    implicit val _createDiv: CreateDiv[F] = SyncCreateDiv[F]
    implicit val _createH1: CreateH1[F] = SyncCreateH1[F]
    implicit val _updateHTMLClass: UpdateHTMLClass[F] = SyncUpdateHTMLClass[F]

    InformationText()
  }

  def apply[
    F[_]: Sync: AppendElement: AppendTextNode: CreateDiv: CreateH1: UpdateHTMLClass
  ]()(implicit document: HTMLDocument): F[HTMLDivElement] =
    for {
      containerDiv <- CreateDiv[F].create
      _ <- UpdateHTMLClass[F].update(containerDiv, CSSClass.InformationText)
      informationText <- CreateH1[F].create
      _ <- AppendTextNode[F].append(informationText, Text.CurrentlyUnderDevelopment)
      _ <- AppendElement[F].append(containerDiv, informationText)
    } yield containerDiv

}
