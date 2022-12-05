package net.st915.minesweeper.logic.refreshui.impl

import cats.effect.Sync
import net.st915.minesweeper.Consts.CSSClass
import net.st915.minesweeper.Coordinate
import net.st915.minesweeper.logic.refreshui.application.*
import org.scalajs.dom.*

class SyncUpdateFlagPlaceholderIconVisibility[F[_]: Sync: UpdateIconContainerVisibility]
    extends UpdateFlagPlaceholderIconVisibility[F] {

  import cats.syntax.flatMap.*

  override def update(coord: Coordinate, visible: Boolean)(implicit
  document: HTMLDocument): F[Unit] =
    UpdateIconContainerVisibility[F].update(
      CSSClass.FlagPlaceholderIcon,
      coord,
      visible
    )

}