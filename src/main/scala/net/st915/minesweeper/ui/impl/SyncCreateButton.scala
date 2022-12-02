package net.st915.minesweeper.ui.impl

import cats.effect.{IO, Sync}
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.Consts.CSSClass
import net.st915.minesweeper.ui.application.*
import org.scalajs.dom.*

class SyncCreateButton[
  F[
    _
  ]: Sync: AppendElement: AppendTextNode: CreateElement: UpdateElementClickEvent: UpdateElementID: UpdateElementRightClickEvent: UpdateHTMLClass
] extends CreateButton[F] {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  override def create(text: String, id: String, onClick: IO[Unit])(
    implicit document: HTMLDocument,
    runtime: IORuntime
  ): F[HTMLDivElement] =
    for {
      button <- CreateElement[F].create[HTMLDivElement]("div")
      _ <- UpdateHTMLClass[F].update(button, CSSClass.Button)
      innerText <- CreateElement[F].create[HTMLSpanElement]("span")
      _ <- AppendTextNode[F].append(innerText, text)
      _ <- UpdateHTMLClass[F].update(innerText, CSSClass.ButtonInner)
      _ <- UpdateElementID[F].update(innerText, s"btn_$id")
      _ <- UpdateElementClickEvent[F].update(innerText, onClick)
      _ <- UpdateElementRightClickEvent[F].update(innerText, IO.unit)
      _ <- AppendElement[F].append(button, innerText)
    } yield button

}
