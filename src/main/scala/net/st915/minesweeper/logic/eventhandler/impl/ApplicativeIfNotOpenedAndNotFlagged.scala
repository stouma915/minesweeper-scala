package net.st915.minesweeper.logic.eventhandler.impl

import cats.Applicative
import net.st915.minesweeper.logic.eventhandler.application.*
import net.st915.minesweeper.{Coordinate, GameState}

class ApplicativeIfNotOpenedAndNotFlagged[F[_]: Applicative: IfOpened: IfFlagged: DoNothing]
    extends IfNotOpenedAndNotFlagged[F] {

  override def perform(coord: Coordinate)(program: => F[GameState])(implicit
  gameState: GameState): F[GameState] =
    IfOpened[F].perform(coord) {
      DoNothing[F].perform
    } {
      IfFlagged[F].perform(coord) {
        DoNothing[F].perform
      } {
        program
      }
    }

}
