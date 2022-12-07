package net.st915.minesweeper.ui.components

import cats.effect.Sync
import net.st915.minesweeper.ui.components.typeclasses.*
import net.st915.minesweeper.ui.components.instances.*
import net.st915.minesweeper.ui.consts.CSSClass
import org.scalajs.dom.*

object FlagIcon {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](using HTMLDocument): F[HTMLDivElement] = {
    given CanAppendElement[F] = SyncCanAppendElement[F]
    given CanCreateElement[F, HTMLDivElement] = MonadCanCreateElementDiv[F]
    given CanUpdateElementClass[F] = SyncCanUpdateElementClass[F]

    for {
      containerDiv <- CanCreateElement[F, HTMLDivElement].create
      _ <- CanUpdateElementClass[F].perform(containerDiv, CSSClass.FlagIcon)

      partTop <- CanCreateElement[F, HTMLDivElement].create
      _ <- CanUpdateElementClass[F].perform(partTop, CSSClass.FlagPartTop)
      _ <- CanAppendElement[F].perform(containerDiv, partTop)

      partMiddle <- CanCreateElement[F, HTMLDivElement].create
      _ <- CanUpdateElementClass[F].perform(partMiddle, CSSClass.FlagPartMiddle)
      _ <- CanAppendElement[F].perform(containerDiv, partMiddle)

      partBottom <- CanCreateElement[F, HTMLDivElement].create
      _ <- CanUpdateElementClass[F].perform(partBottom, CSSClass.FlagPartBottom)
      _ <- CanAppendElement[F].perform(containerDiv, partBottom)
    } yield containerDiv
  }

}

class FlagIcon extends CellIcon
