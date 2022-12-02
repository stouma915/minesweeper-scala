package net.st915.minesweeper.ui.component.impl

import cats.effect.Sync
import net.st915.minesweeper.Consts.CSSClass
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.component.application.MineCountContainer
import org.scalajs.dom.*

class SyncMineCountContainer[
  F[_]: Sync: AppendTextNode: CreateDiv: GetMineCountColor: UpdateElementID: UpdateElementTextColor: UpdateHTMLClass
] extends MineCountContainer[F] {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  override def create(id: String, num: Int)(implicit document: HTMLDocument): F[HTMLDivElement] =
    for {
      mineCountContainer <- CreateDiv[F].create
      _ <- UpdateHTMLClass[F].update(mineCountContainer, CSSClass.MineCountContainer)
      _ <- UpdateElementID[F].update(mineCountContainer, id)
      _ <- AppendTextNode[F].append(mineCountContainer, num.toString)
      textColor <- GetMineCountColor[F].get(num)
      _ <- UpdateElementTextColor[F].update(mineCountContainer, textColor)
    } yield mineCountContainer

}
