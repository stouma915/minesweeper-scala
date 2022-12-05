package net.st915.minesweeper.logic.refreshui.impl

import cats.effect.Sync
import net.st915.minesweeper.Coordinate
import net.st915.minesweeper.logic.refreshui.application.*
import org.scalajs.dom.*

class SyncHideFlagPlaceholderIcon[F[_]: Sync: UpdateFlagPlaceholderIconVisibility]
    extends HideFlagPlaceholderIcon[F] {

  override def perform(coord: Coordinate)(implicit document: HTMLDocument): F[Unit] =
    UpdateFlagPlaceholderIconVisibility[F].update(coord, false)

}
