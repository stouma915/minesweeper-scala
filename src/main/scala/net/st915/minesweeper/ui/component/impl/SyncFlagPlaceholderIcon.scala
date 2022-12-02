package net.st915.minesweeper.ui.component.impl

import cats.effect.Sync
import net.st915.minesweeper.Consts.CSSClass
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.component.application.FlagPlaceholderIcon
import org.scalajs.dom.*

class SyncFlagPlaceholderIcon[
  F[_]: Sync: AppendElement: CreateDiv: UpdateHTMLClass
] extends FlagPlaceholderIcon[F] {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  override def create(implicit document: HTMLDocument): F[HTMLDivElement] =
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
