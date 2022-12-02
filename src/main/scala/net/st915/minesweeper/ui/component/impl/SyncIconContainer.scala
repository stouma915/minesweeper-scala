package net.st915.minesweeper.ui.component.impl

import cats.effect.Sync
import net.st915.minesweeper.Consts.{CSSClass, ElementID}
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.component.application.IconContainer
import org.scalajs.dom.*

class SyncIconContainer[
  F[_]: Sync: AppendElement: CreateDiv: UpdateElementID: UpdateHTMLClass
] extends IconContainer[F] {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  override def create[A <: HTMLElement](id: String, icon: A)(implicit
  document: HTMLDocument): F[HTMLDivElement] =
    for {
      iconContainer <- CreateDiv[F].create
      _ <- UpdateHTMLClass[F].update(iconContainer, CSSClass.IconContainer)
      _ <- UpdateElementID[F].update(iconContainer, s"${ElementID.IconContainerPrefix}$id")
      _ <- AppendElement[F].append(iconContainer, icon)
    } yield iconContainer

}
