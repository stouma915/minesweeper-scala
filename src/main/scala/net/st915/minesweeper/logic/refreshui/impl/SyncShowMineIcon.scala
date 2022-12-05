package net.st915.minesweeper.logic.refreshui.impl

import cats.effect.Sync
import net.st915.minesweeper.Coordinate
import net.st915.minesweeper.logic.refreshui.application.*
import org.scalajs.dom.*

class SyncShowMineIcon[F[_]: Sync: UpdateMineIconVisibility] extends ShowMineIcon[F] {

  override def perform(coord: Coordinate)(implicit document: HTMLDocument): F[Unit] =
    UpdateMineIconVisibility[F].update(coord, true)

}
