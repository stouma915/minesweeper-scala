package net.st915.minesweeper.ui.component

import cats.effect.Sync
import net.st915.minesweeper.Consts.CSSClass
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.impl.*
import org.scalajs.dom.*

object MineIcon {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](implicit document: HTMLDocument): F[HTMLDivElement] = {
    implicit val _appendElement: AppendElement[F] = SyncAppendElement[F]
    implicit val _createElement: CreateElement[F] = SyncCreateElement[F]
    implicit val _createDiv: CreateDiv[F] = SyncCreateDiv[F]
    implicit val _updateHTMLClass: UpdateHTMLClass[F] = SyncUpdateHTMLClass[F]

    MineIcon()
  }

  def apply[F[_]: Sync: AppendElement: CreateDiv: UpdateHTMLClass]()(implicit
  document: HTMLDocument): F[HTMLDivElement] =
    for {
      mineIcon <- CreateDiv[F].create
      _ <- UpdateHTMLClass[F].update(mineIcon, CSSClass.MineIcon)
      minePartTop <- CreateDiv[F].create
      _ <- UpdateHTMLClass[F].update(minePartTop, CSSClass.MinePartTop)
      _ <- AppendElement[F].append(mineIcon, minePartTop)
      minePartMiddleLeft <- CreateDiv[F].create
      _ <- UpdateHTMLClass[F].update(minePartMiddleLeft, CSSClass.MinePartMiddleLeft)
      _ <- AppendElement[F].append(mineIcon, minePartMiddleLeft)
      minePartMiddleCenter <- CreateDiv[F].create
      _ <- UpdateHTMLClass[F].update(minePartMiddleCenter, CSSClass.MinePartMiddleCenter)
      _ <- AppendElement[F].append(mineIcon, minePartMiddleCenter)
    } yield mineIcon

}
