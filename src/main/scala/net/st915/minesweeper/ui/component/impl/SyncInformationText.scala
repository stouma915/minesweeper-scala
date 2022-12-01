package net.st915.minesweeper.ui.component.impl

import cats.effect.Sync
import net.st915.minesweeper.ui.application.{
  AppendElement,
  AppendTextNode,
  CreateElement,
  UpdateHTMLClass
}
import net.st915.minesweeper.ui.component.application.InformationText
import org.scalajs.dom.{HTMLDivElement, HTMLDocument, HTMLElement}

class SyncInformationText[F[
    _
]: Sync: AppendElement: AppendTextNode: CreateElement: UpdateHTMLClass]
    extends InformationText[F] {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  override def create(implicit document: HTMLDocument): F[HTMLDivElement] =
    for {
      containerDiv <- CreateElement[F].create[HTMLDivElement]("div")
      _ <- UpdateHTMLClass[F].update(containerDiv, "informationTextContainer")
      informationText <- CreateElement[F].create[HTMLElement]("h1")
      _ <- AppendTextNode[F].append(
        informationText,
        "Currently Under Development."
      )
      _ <- AppendElement[F].append(containerDiv, informationText)
    } yield containerDiv

}
