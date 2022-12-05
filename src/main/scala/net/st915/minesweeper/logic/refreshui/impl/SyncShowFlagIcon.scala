package net.st915.minesweeper.logic.refreshui.impl

import cats.effect.Sync
import net.st915.minesweeper.Coordinate
import net.st915.minesweeper.logic.refreshui.application.*
import org.scalajs.dom.*

class SyncShowFlagIcon[F[_]: Sync: UpdateFlagIconVisibility] extends ShowFlagIcon[F] {

  override def perform(coord: Coordinate)(implicit document: HTMLDocument): F[Unit] =
    UpdateFlagIconVisibility[F].update(coord, true)

}