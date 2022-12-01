package net.st915.minesweeper.ui.component.impl

import cats.effect.Sync
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.component.application.MineCountContainer
import org.scalajs.dom.*

class SyncMineCountContainer[
  F[
    _
  ]: Sync: AppendTextNode: CreateElement: GetMineCountColor: UpdateElementID: UpdateElementTextColor: UpdateHTMLClass
] extends MineCountContainer[F] {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  override def create(id: String, num: Int)(implicit document: HTMLDocument): F[HTMLDivElement] =
    for {
      mineCountContainer <- CreateElement[F].create[HTMLDivElement]("div")
      _ <- UpdateHTMLClass[F].update(mineCountContainer, "mineCountContainer")
      _ <- UpdateElementID[F].update(
        mineCountContainer,
        s"mineCountContainer_$id"
      )
      _ <- AppendTextNode[F].append(mineCountContainer, num.toString)
      textColor <- GetMineCountColor[F].get(num)
      _ <- UpdateElementTextColor[F].update(mineCountContainer, textColor)
    } yield mineCountContainer

}
