package net.st915.minesweeper.ui.components

import cats.effect.Sync
import net.st915.minesweeper.ui.components.typeclasses.*
import net.st915.minesweeper.ui.components.instances.*
import net.st915.minesweeper.ui.consts.CSSClasses
import org.scalajs.dom.*

object FlagPlaceholderIcon {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](using HTMLDocument): F[HTMLDivElement] = {
    given CanAppendElement[F] = SyncCanAppendElement[F]
    given CanCreateElement[F, HTMLDivElement] = MonadCanCreateElementDiv[F]
    given CanUpdateElementClass[F] = SyncCanUpdateElementClass[F]

    for {
      containerDiv <- CanCreateElement[F, HTMLDivElement].create
      _ <- CanUpdateElementClass[F].perform(containerDiv, CSSClasses.FlagPlaceholderIcon)

      partTop <- CanCreateElement[F, HTMLDivElement].create
      _ <- CanUpdateElementClass[F].perform(partTop, CSSClasses.FlagPlaceholderPartTop)
      _ <- CanAppendElement[F].perform(containerDiv, partTop)

      partMiddle <- CanCreateElement[F, HTMLDivElement].create
      _ <- CanUpdateElementClass[F].perform(partMiddle, CSSClasses.FlagPlaceholderPartMiddle)
      _ <- CanAppendElement[F].perform(containerDiv, partMiddle)

      partBottom <- CanCreateElement[F, HTMLDivElement].create
      _ <- CanUpdateElementClass[F].perform(partBottom, CSSClasses.FlagPlaceholderPartBottom)
      _ <- CanAppendElement[F].perform(containerDiv, partBottom)
    } yield containerDiv
  }

}

class FlagPlaceholderIcon extends CellIcon
