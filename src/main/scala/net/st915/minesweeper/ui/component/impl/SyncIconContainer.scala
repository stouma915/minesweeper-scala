package net.st915.minesweeper.ui.component.impl

import cats.effect.Sync
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.component.application.IconContainer
import org.scalajs.dom.*

class SyncIconContainer[
  F[_]: Sync: AppendElement: CreateElement: UpdateElementID: UpdateHTMLClass
] extends IconContainer[F] {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  override def create[A <: HTMLElement](id: String, icon: A)(implicit
  document: HTMLDocument): F[HTMLDivElement] =
    for {
      iconContainer <- CreateElement[F].create[HTMLDivElement]("div")
      _ <- UpdateHTMLClass[F].update(iconContainer, "iconContainer")
      _ <- UpdateElementID[F].update(iconContainer, s"iconContainer_$id")
      _ <- AppendElement[F].append(iconContainer, icon)
    } yield iconContainer

}
