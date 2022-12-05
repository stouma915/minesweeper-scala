package net.st915.minesweeper.logic.eventhandler

import cats.effect.Sync
import net.st915.minesweeper.GameState
import net.st915.minesweeper.event.CellClickEvent
import net.st915.minesweeper.logic.application.*
import net.st915.minesweeper.logic.eventhandler.application.*
import net.st915.minesweeper.logic.eventhandler.impl.*
import net.st915.minesweeper.logic.impl.*

object HandleCellClickEvent {

  def wired[F[_]: Sync](event: CellClickEvent)(implicit gameState: GameState): F[GameState] = {
    implicit val _ifFlagged: IfFlagged[F] = ApplicativeIfFlagged[F]
    implicit val _ifOpened: IfOpened[F] = ApplicativeIfOpened[F]
    implicit val _ifGameStarted: IfGameStarted[F] = ApplicativeIfGameStarted[F]
    implicit val _ifGameStopped: IfGameStopped[F] = ApplicativeIfGameStopped[F]

    implicit val _doNothing: DoNothing[F] = ApplicativeDoNothing[F]
    implicit val _addOpenedCoord: AddOpenedCoord[F] = ApplicativeAddOpenedCoord[F]
    implicit val _ifCanOpenOperation: IfCanOpenOperation[F] = ApplicativeIfCanOpenOperation[F]
    implicit val _ifNotOpenedAndNotFlagged: IfNotOpenedAndNotFlagged[F] =
      ApplicativeIfNotOpenedAndNotFlagged[F]

    HandleCellClickEvent(event)
  }

  def apply[
    F[_]: Sync: IfCanOpenOperation: IfNotOpenedAndNotFlagged: AddOpenedCoord: DoNothing
  ](event: CellClickEvent)(implicit gameState: GameState): F[GameState] = {
    val coord = event.coord

    IfCanOpenOperation[F].perform {
      IfNotOpenedAndNotFlagged[F].perform(coord) {
        AddOpenedCoord[F].add(coord)
      }
    } {
      // TODO: Write start game logic here
      DoNothing[F].perform
    }
  }
}
