package net.st915.minesweeper.ui.component

import cats.effect.Sync
import net.st915.minesweeper.Consts.CSSClass
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.impl.*
import org.scalajs.dom.*

object MineCountContainer {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](id: String, num: Int)(implicit
  document: HTMLDocument): F[HTMLDivElement] = {
    implicit val _createTextNode: CreateTextNode[F] = SyncCreateTextNode[F]
    implicit val _appendTextNode: AppendTextNode[F] = SyncAppendTextNode[F]
    implicit val _createElement: CreateElement[F] = SyncCreateElement[F]
    implicit val _createDiv: CreateDiv[F] = SyncCreateDiv[F]
    implicit val _getMineCountColor: GetMineCountColor[F] = ApplicativeGetMineCountColor[F]
    implicit val _updateElementID: UpdateElementID[F] = SyncUpdateElementID[F]
    implicit val _updateElementTextColor: UpdateElementTextColor[F] = SyncUpdateElementTextColor[F]
    implicit val _updateHTMLClass: UpdateHTMLClass[F] = SyncUpdateHTMLClass[F]

    MineCountContainer(id, num)
  }

  def apply[
    F[
      _
    ]: Sync: AppendTextNode: CreateDiv: GetMineCountColor: UpdateElementID: UpdateElementTextColor: UpdateHTMLClass
  ](id: String, num: Int)(implicit document: HTMLDocument): F[HTMLDivElement] =
    for {
      mineCountContainer <- CreateDiv[F].create
      _ <- UpdateHTMLClass[F].update(mineCountContainer, CSSClass.MineCountContainer)
      _ <- UpdateElementID[F].update(mineCountContainer, id)
      _ <- AppendTextNode[F].append(mineCountContainer, num.toString)
      textColor <- GetMineCountColor[F].get(num)
      _ <- UpdateElementTextColor[F].update(mineCountContainer, textColor)
    } yield mineCountContainer
}
