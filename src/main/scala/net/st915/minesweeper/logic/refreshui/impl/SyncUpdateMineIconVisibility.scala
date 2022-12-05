package net.st915.minesweeper.logic.refreshui.impl

import cats.effect.Sync
import net.st915.minesweeper.Consts.CSSClass
import net.st915.minesweeper.Coordinate
import net.st915.minesweeper.logic.refreshui.application.*
import org.scalajs.dom.*

class SyncUpdateMineIconVisibility[F[_]: Sync: UpdateIconContainerVisibility]
    extends UpdateMineIconVisibility[F] {

  override def update(coord: Coordinate, visible: Boolean)(implicit
  document: HTMLDocument): F[Unit] =
    UpdateIconContainerVisibility[F].update(
      CSSClass.MineIcon,
      coord,
      visible
    )

}
