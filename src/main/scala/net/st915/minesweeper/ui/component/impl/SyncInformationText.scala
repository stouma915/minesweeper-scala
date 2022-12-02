package net.st915.minesweeper.ui.component.impl

import cats.effect.Sync
import net.st915.minesweeper.Consts.{CSSClass, Text}
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.component.application.InformationText
import org.scalajs.dom.*

class SyncInformationText[
  F[_]: Sync: AppendElement: AppendTextNode: CreateDiv: CreateH1: UpdateHTMLClass
] extends InformationText[F] {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  override def create(implicit document: HTMLDocument): F[HTMLDivElement] =
    for {
      containerDiv <- CreateDiv[F].create
      _ <- UpdateHTMLClass[F].update(containerDiv, CSSClass.InformationText)
      informationText <- CreateH1[F].create
      _ <- AppendTextNode[F].append(informationText, Text.CurrentlyUnderDevelopment)
      _ <- AppendElement[F].append(containerDiv, informationText)
    } yield containerDiv

}
