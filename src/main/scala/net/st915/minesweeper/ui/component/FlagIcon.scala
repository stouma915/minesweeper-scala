package net.st915.minesweeper.ui.component

import cats.effect.Sync
import net.st915.minesweeper.Consts.CSSClass
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.impl.*
import org.scalajs.dom.*

object FlagIcon {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](implicit document: HTMLDocument): F[HTMLDivElement] = {
    implicit val _appendElement: AppendElement[F] = SyncAppendElement[F]
    implicit val _createElement: CreateElement[F] = SyncCreateElement[F]
    implicit val _createDiv: CreateDiv[F] = SyncCreateDiv[F]
    implicit val _updateHTMLClass: UpdateHTMLClass[F] = SyncUpdateHTMLClass[F]

    FlagIcon()
  }

  def apply[
    F[_]: Sync: AppendElement: CreateDiv: UpdateHTMLClass
  ]()(implicit document: HTMLDocument): F[HTMLDivElement] =
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
