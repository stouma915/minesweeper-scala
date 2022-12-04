package net.st915.minesweeper.ui.component

import cats.effect.Sync
import net.st915.minesweeper.Consts.CSSClass
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.impl.*
import org.scalajs.dom.*

object FlagPlaceholderIcon {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](implicit document: HTMLDocument): F[HTMLDivElement] = {
    implicit val _appendElement: AppendElement[F] = SyncAppendElement[F]
    implicit val _createElement: CreateElement[F] = SyncCreateElement[F]
    implicit val _createDiv: CreateDiv[F] = SyncCreateDiv[F]
    implicit val _updateHTMLClass: UpdateHTMLClass[F] = SyncUpdateHTMLClass[F]

    FlagPlaceholderIcon()
  }

  def apply[
    F[_]: Sync: AppendElement: CreateDiv: UpdateHTMLClass
  ]()(implicit document: HTMLDocument): F[HTMLDivElement] =
    for {
      icon <- CreateDiv[F].create
      _ <- UpdateHTMLClass[F].update(icon, CSSClass.FlagPlaceholderIcon)
      partTop <- CreateDiv[F].create
      _ <- UpdateHTMLClass[F].update(partTop, CSSClass.FlagPlaceholderPartTop)
      _ <- AppendElement[F].append(icon, partTop)
      partMiddle <- CreateDiv[F].create
      _ <- UpdateHTMLClass[F].update(partMiddle, CSSClass.FlagPlaceholderPartMiddle)
      _ <- AppendElement[F].append(icon, partMiddle)
      partBottom <- CreateDiv[F].create
      _ <- UpdateHTMLClass[F].update(partBottom, CSSClass.FlagPlaceholderPartBottom)
      _ <- AppendElement[F].append(icon, partBottom)
    } yield icon

}
