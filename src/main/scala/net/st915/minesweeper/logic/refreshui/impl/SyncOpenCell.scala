package net.st915.minesweeper.logic.refreshui.impl

import cats.effect.Sync
import net.st915.minesweeper.Coordinate
import net.st915.minesweeper.logic.refreshui.application.*
import org.scalajs.dom.*

class SyncOpenCell[F[_]: Sync: UpdateCellOpening] extends OpenCell[F] {

  override def perform(coord: Coordinate)(implicit document: HTMLDocument): F[Unit] =
    UpdateCellOpening[F].update(coord, true)

}
