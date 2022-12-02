package net.st915.minesweeper.ui.component.impl

import cats.effect.Sync
import net.st915.minesweeper.Consts.CSSClass
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.component.application.MineIcon
import org.scalajs.dom.*

class SyncMineIcon[
  F[_]: Sync: AppendElement: CreateElement: UpdateHTMLClass
] extends MineIcon[F] {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  override def create(implicit document: HTMLDocument): F[HTMLDivElement] =
    for {
      mineIcon <- CreateElement[F].create[HTMLDivElement]("div")
      _ <- UpdateHTMLClass[F].update(mineIcon, CSSClass.MineIcon)
      minePartTop <- CreateElement[F].create[HTMLDivElement]("div")
      _ <- UpdateHTMLClass[F].update(minePartTop, CSSClass.MinePartTop)
      _ <- AppendElement[F].append(mineIcon, minePartTop)
      minePartMiddleLeft <- CreateElement[F].create[HTMLDivElement]("div")
      _ <- UpdateHTMLClass[F].update(minePartMiddleLeft, CSSClass.MinePartMiddleLeft)
      _ <- AppendElement[F].append(mineIcon, minePartMiddleLeft)
      minePartMiddleCenter <- CreateElement[F].create[HTMLDivElement]("div")
      _ <- UpdateHTMLClass[F].update(minePartMiddleCenter, CSSClass.MinePartMiddleCenter)
      _ <- AppendElement[F].append(mineIcon, minePartMiddleCenter)
    } yield mineIcon

}
