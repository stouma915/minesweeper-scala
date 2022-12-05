package net.st915.minesweeper.logic.eventhandler

import cats.effect.Sync
import net.st915.minesweeper.GameState
import net.st915.minesweeper.event.CellRightClickEvent
import net.st915.minesweeper.logic.application.*
import net.st915.minesweeper.logic.eventhandler.application.*
import net.st915.minesweeper.logic.eventhandler.impl.*
import net.st915.minesweeper.logic.impl.*

object HandleCellRightClickEvent {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](event: CellRightClickEvent)(implicit gameState: GameState): F[GameState] = {
    implicit val _ifFlagged: IfFlagged[F] = ApplicativeIfFlagged[F]
    implicit val _ifOpened: IfOpened[F] = ApplicativeIfOpened[F]
    implicit val _ifGameStarted: IfGameStarted[F] = ApplicativeIfGameStarted[F]
    implicit val _ifGameStopped: IfGameStopped[F] = ApplicativeIfGameStopped[F]

    implicit val _doNothing: DoNothing[F] = ApplicativeDoNothing[F]
    implicit val _addFlaggedCoord: AddFlaggedCoord[F] = ApplicativeAddFlaggedCoord[F]
    implicit val _removeFlaggedCoord: RemoveFlaggedCoord[F] = ApplicativeRemoveFlaggedCoord[F]
    implicit val _ifCanOperation: IfCanOperation[F] = ApplicativeIfCanOperation[F]
    implicit val _ifCanFlagOperation: IfCanFlagOperation[F] = ApplicativeIfCanFlagOperation[F]

    HandleCellRightClickEvent(event)
  }

  def apply[
    F[_]: Sync: IfCanFlagOperation: IfFlagged: AddFlaggedCoord: RemoveFlaggedCoord
  ](event: CellRightClickEvent)(implicit gameState: GameState): F[GameState] = {
    val coord = event.coord
    IfCanFlagOperation[F].perform(coord) {
      IfFlagged[F].perform(coord) {
        RemoveFlaggedCoord[F].remove(coord)
      } {
        AddFlaggedCoord[F].add(coord)
      }
    }
  }

}
