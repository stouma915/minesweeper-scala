package net.st915.minesweeper.logic.eventhandler

import cats.effect.Sync
import net.st915.minesweeper.GameState
import net.st915.minesweeper.event.CellClickEvent
import net.st915.minesweeper.logic.*
import net.st915.minesweeper.logic.application.IfInFlagPlaceMode
import net.st915.minesweeper.logic.impl.ApplicativeIfInFlagPlaceMode

object HandleCellClickEvent {

  def wired[F[_]: Sync](event: CellClickEvent)(implicit gameState: GameState): F[GameState] = {
    implicit val _ifInFlagPlaceMode: IfInFlagPlaceMode[F] = ApplicativeIfInFlagPlaceMode[F]

    HandleCellClickEvent(event)
  }

  def apply[
    F[_]: Sync: IfInFlagPlaceMode
  ](event: CellClickEvent)(implicit gameState: GameState): F[GameState] =
    IfInFlagPlaceMode[F].perform {
      PlaceFlagLogic.wired[F](event.coord)
    } {
      OpenCellLogic.wired[F](event.coord)
    }
}
