package net.st915.minesweeper.logic

import cats.effect.Sync
import net.st915.minesweeper.{Coordinate, GameState}
import net.st915.minesweeper.logic.application.*
import net.st915.minesweeper.logic.impl.*

object PlaceFlagLogic {

  def wired[F[_]: Sync](coord: Coordinate)(implicit gameState: GameState): F[GameState] = {
    implicit val _ifFlagged: IfFlagged[F] = ApplicativeIfFlagged[F]
    implicit val _ifOpened: IfOpened[F] = ApplicativeIfOpened[F]
    implicit val _ifGameStarted: IfGameStarted[F] = ApplicativeIfGameStarted[F]
    implicit val _ifGameStopped: IfGameStopped[F] = ApplicativeIfGameStopped[F]

    implicit val _doNothing: DoNothing[F] = ApplicativeDoNothing[F]
    implicit val _addFlaggedCoord: AddFlaggedCoord[F] = ApplicativeAddFlaggedCoord[F]
    implicit val _removeFlaggedCoord: RemoveFlaggedCoord[F] = ApplicativeRemoveFlaggedCoord[F]
    implicit val _ifCanOperation: IfCanOperation[F] = ApplicativeIfCanOperation[F]
    implicit val _ifCanFlagOperation: IfCanFlagOperation[F] = ApplicativeIfCanFlagOperation[F]

    PlaceFlagLogic(coord)
  }

  def apply[
    F[_]: Sync: IfCanFlagOperation: IfFlagged: AddFlaggedCoord: RemoveFlaggedCoord
  ](coord: Coordinate)(implicit gameState: GameState): F[GameState] =
    IfCanFlagOperation[F].perform(coord) {
      IfFlagged[F].perform(coord) {
        RemoveFlaggedCoord[F].remove(coord)
      } {
        AddFlaggedCoord[F].add(coord)
      }
    }

}
