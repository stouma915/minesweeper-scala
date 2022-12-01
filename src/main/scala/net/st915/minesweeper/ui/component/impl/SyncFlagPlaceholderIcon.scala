package net.st915.minesweeper.ui.component.impl

import cats.effect.Sync
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.component.application.FlagPlaceholderIcon
import org.scalajs.dom.*

class SyncFlagPlaceholderIcon[
  F[_]: Sync: AppendElement: CreateElement: UpdateHTMLClass
] extends FlagPlaceholderIcon[F] {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  override def create(implicit document: HTMLDocument): F[HTMLDivElement] =
    for {
      icon <- CreateElement[F].create[HTMLDivElement]("div")
      _ <- UpdateHTMLClass[F].update(icon, "flagPlaceholder")
      partTop <- CreateElement[F].create[HTMLDivElement]("div")
      _ <- UpdateHTMLClass[F].update(
        partTop,
        "flagPlaceholderPart flagPlaceholderTop"
      )
      _ <- AppendElement[F].append(icon, partTop)
      partMiddle <- CreateElement[F].create[HTMLDivElement]("div")
      _ <- UpdateHTMLClass[F].update(
        partMiddle,
        "flagPlaceholderPart flagPlaceholderMiddle"
      )
      _ <- AppendElement[F].append(icon, partMiddle)
      partBottom <- CreateElement[F].create[HTMLDivElement]("div")
      _ <- UpdateHTMLClass[F].update(
        partBottom,
        "flagPlaceholderPart flagPlaceholderBottom"
      )
      _ <- AppendElement[F].append(icon, partBottom)
    } yield icon

}
