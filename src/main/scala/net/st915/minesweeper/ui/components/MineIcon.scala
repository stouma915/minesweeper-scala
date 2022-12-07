package net.st915.minesweeper.ui.components

import cats.effect.Sync
import net.st915.minesweeper.ui.components.typeclasses.*
import net.st915.minesweeper.ui.components.instances.*
import net.st915.minesweeper.ui.consts.CSSClasses
import org.scalajs.dom.*

object MineIcon {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](using HTMLDocument): F[HTMLDivElement] = {
    given CanAppendElement[F] = SyncCanAppendElement[F]
    given CanCreateElement[F, HTMLDivElement] = MonadCanCreateElementDiv[F]
    given CanUpdateElementClass[F] = SyncCanUpdateElementClass[F]

    for {
      containerDiv <- CanCreateElement[F, HTMLDivElement].create
      _ <- CanUpdateElementClass[F].perform(containerDiv, CSSClasses.MineIcon)

      partTop <- CanCreateElement[F, HTMLDivElement].create
      _ <- CanUpdateElementClass[F].perform(partTop, CSSClasses.MinePartTop)
      _ <- CanAppendElement[F].perform(containerDiv, partTop)

      partMiddleLeft <- CanCreateElement[F, HTMLDivElement].create
      _ <- CanUpdateElementClass[F].perform(partMiddleLeft, CSSClasses.MinePartMiddleLeft)
      _ <- CanAppendElement[F].perform(containerDiv, partMiddleLeft)

      partMiddleCenter <- CanCreateElement[F, HTMLDivElement].create
      _ <- CanUpdateElementClass[F].perform(partMiddleCenter, CSSClasses.MinePartMiddleCenter)
      _ <- CanAppendElement[F].perform(containerDiv, partMiddleCenter)
    } yield containerDiv
  }

}

class MineIcon extends CellIcon
