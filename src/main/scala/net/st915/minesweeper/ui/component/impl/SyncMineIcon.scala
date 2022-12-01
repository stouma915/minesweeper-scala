package net.st915.minesweeper.ui.component.impl

import cats.effect.Sync
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.component.application.MineIcon
import org.scalajs.dom.*

class SyncMineIcon[F[_]: Sync: AppendElement: CreateElement: UpdateHTMLClass]
    extends MineIcon[F] {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  override def create(implicit document: HTMLDocument): F[HTMLDivElement] =
    for {
      mineIcon <- CreateElement[F].create[HTMLDivElement]("div")
      _ <- UpdateHTMLClass[F].update(mineIcon, "mine")
      minePartTop <- CreateElement[F].create[HTMLDivElement]("div")
      _ <- UpdateHTMLClass[F].update(minePartTop, "minePart mineTop")
      _ <- AppendElement[F].append(mineIcon, minePartTop)
      minePartMiddleLeft <- CreateElement[F].create[HTMLDivElement]("div")
      _ <- UpdateHTMLClass[F].update(
        minePartMiddleLeft,
        "minePart mineMiddleLeft"
      )
      _ <- AppendElement[F].append(mineIcon, minePartMiddleLeft)
      minePartMiddleCenter <- CreateElement[F].create[HTMLDivElement]("div")
      _ <- UpdateHTMLClass[F].update(
        minePartMiddleCenter,
        "minePart mineMiddleCenter"
      )
      _ <- AppendElement[F].append(mineIcon, minePartMiddleCenter)
    } yield mineIcon

}
