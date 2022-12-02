package net.st915.minesweeper.ui.component.impl

import cats.effect.Sync
import net.st915.minesweeper.Consts.CSSClass
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.component.application.FlagIcon
import org.scalajs.dom.*

class SyncFlagIcon[
  F[_]: Sync: AppendElement: CreateDiv: UpdateHTMLClass
] extends FlagIcon[F] {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  override def create(implicit document: HTMLDocument): F[HTMLDivElement] =
    for {
      flagIcon <- CreateDiv[F].create
      _ <- UpdateHTMLClass[F].update(flagIcon, CSSClass.FlagIcon)
      flagPartTop <- CreateDiv[F].create
      _ <- UpdateHTMLClass[F].update(flagPartTop, CSSClass.FlagPartTop)
      _ <- AppendElement[F].append(flagIcon, flagPartTop)
      flagPartMiddle <- CreateDiv[F].create
      _ <- UpdateHTMLClass[F].update(flagPartMiddle, CSSClass.FlagPartMiddle)
      _ <- AppendElement[F].append(flagIcon, flagPartMiddle)
      flagPartBottom <- CreateDiv[F].create
      _ <- UpdateHTMLClass[F].update(flagPartBottom, CSSClass.FlagPartBottom)
      _ <- AppendElement[F].append(flagIcon, flagPartBottom)
    } yield flagIcon

}
