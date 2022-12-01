package net.st915.minesweeper.ui.component.impl

import cats.effect.{IO, Sync}
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.component.application.Button
import org.scalajs.dom.*

class SyncButton[
  F[
    _
  ]: Sync: AppendElement: AppendTextNode: CreateElement: UpdateElementClickEvent: UpdateElementID: UpdateElementRightClickEvent: UpdateHTMLClass
] extends Button[F] {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  override def create(text: String, id: String, onClick: IO[Unit])(
    implicit document: HTMLDocument,
    runtime: IORuntime
  ): F[HTMLDivElement] =
    for {
      button <- CreateElement[F].create[HTMLDivElement]("div")
      _ <- UpdateHTMLClass[F].update(button, "btn")
      innerText <- CreateElement[F].create[HTMLSpanElement]("div")
      _ <- AppendTextNode[F].append(innerText, text)
      _ <- UpdateHTMLClass[F].update(innerText, "btnText")
      _ <- UpdateElementID[F].update(innerText, s"btn_$id")
      _ <- UpdateElementClickEvent[F].update(innerText, onClick)
      _ <- UpdateElementRightClickEvent[F].update(innerText, IO.unit)
      _ <- AppendElement[F].append(button, innerText)
    } yield button

}
