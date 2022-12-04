package net.st915.minesweeper.ui.component

import cats.effect.Sync
import net.st915.minesweeper.Consts.CSSClass
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.impl.*
import org.scalajs.dom.*

object IconContainer {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync, A <: HTMLElement](id: String, icon: A)(implicit
  document: HTMLDocument): F[HTMLDivElement] = {
    implicit val _appendElement: AppendElement[F] = SyncAppendElement[F]
    implicit val _createElement: CreateElement[F] = SyncCreateElement[F]
    implicit val _createDiv: CreateDiv[F] = SyncCreateDiv[F]
    implicit val _updateElementID: UpdateElementID[F] = SyncUpdateElementID[F]
    implicit val _updateHTMLClass: UpdateHTMLClass[F] = SyncUpdateHTMLClass[F]

    IconContainer(id, icon)
  }

  def apply[
    F[_]: Sync: AppendElement: CreateDiv: UpdateElementID: UpdateHTMLClass,
    A <: HTMLElement
  ](id: String, icon: A)(implicit document: HTMLDocument): F[HTMLDivElement] =
    for {
      iconContainer <- CreateDiv[F].create
      _ <- UpdateHTMLClass[F].update(iconContainer, CSSClass.IconContainer)
      _ <- UpdateElementID[F].update(iconContainer, id)
      _ <- AppendElement[F].append(iconContainer, icon)
    } yield iconContainer

}
