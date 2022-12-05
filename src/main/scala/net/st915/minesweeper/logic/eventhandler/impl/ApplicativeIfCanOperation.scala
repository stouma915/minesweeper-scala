package net.st915.minesweeper.logic.eventhandler.impl

import cats.Applicative
import net.st915.minesweeper.GameState
import net.st915.minesweeper.logic.eventhandler.application.*

class ApplicativeIfCanOperation[
  F[_]: Applicative: IfGameStarted: IfGameStopped: DoNothing
] extends IfCanOperation[F] {

  override def perform(ifCan: => F[GameState])(ifCannot: => F[GameState])(implicit
  gameState: GameState): F[GameState] =
    IfGameStarted[F].perform {
      IfGameStopped[F].perform {
        ifCannot
      } {
        ifCan
      }
    } {
      DoNothing[F].perform
    }

}
