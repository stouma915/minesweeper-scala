package net.st915.minesweeper.ui.impl

import cats.effect.unsafe.IORuntime
import cats.effect.{IO, Sync}
import net.st915.minesweeper.Consts.CSSClass
import net.st915.minesweeper.ui.application.*
import org.scalajs.dom.*

class SyncCreateButton[
  F[_]: Sync: AppendElement: AppendTextNode: CreateDiv: CreateSpan: UpdateElementClickEvent: UpdateElementID: UpdateElementRightClickEvent: UpdateHTMLClass
] extends CreateButton[F] {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  override def create(text: String, id: String, onClick: IO[Unit])(
    implicit document: HTMLDocument,
    runtime: IORuntime
  ): F[HTMLDivElement] =
    for {
      button <- CreateDiv[F].create
      _ <- UpdateHTMLClass[F].update(button, CSSClass.Button)
      innerText <- CreateSpan[F].create
      _ <- AppendTextNode[F].append(innerText, text)
      _ <- UpdateHTMLClass[F].update(innerText, CSSClass.ButtonInner)
      _ <- UpdateElementID[F].update(innerText, id)
      _ <- UpdateElementClickEvent[F].update(innerText, onClick)
      _ <- UpdateElementRightClickEvent[F].update(innerText, IO.unit)
      _ <- AppendElement[F].append(button, innerText)
    } yield button

}
