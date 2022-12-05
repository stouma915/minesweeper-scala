package net.st915.minesweeper.logic.eventhandler.impl

import cats.Applicative
import net.st915.minesweeper.logic.application.IfOpened
import net.st915.minesweeper.logic.eventhandler.application.*
import net.st915.minesweeper.{Coordinate, GameState}

class ApplicativeIfCanFlagOperation[F[_]: Applicative: IfCanOperation: IfOpened: DoNothing]
    extends IfCanFlagOperation[F] {

  override def perform(coord: Coordinate)(program: => F[GameState])(implicit
  gameState: GameState): F[GameState] =
    IfCanOperation[F].perform {
      IfOpened[F].perform(coord) {
        DoNothing[F].perform
      } {
        program
      }
    } {
      DoNothing[F].perform
    }

}