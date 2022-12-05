package net.st915.minesweeper.logic.eventhandler.impl

import cats.Applicative
import net.st915.minesweeper.GameState
import net.st915.minesweeper.logic.application.*
import net.st915.minesweeper.logic.eventhandler.application.*

class ApplicativeIfCanOpenOperation[F[_]: Applicative: IfGameStopped: IfGameStarted: DoNothing]
    extends IfCanOpenOperation[F] {

  override def perform(ifCan: => F[GameState])(ifCannot: => F[GameState])(implicit
  gameState: GameState): F[GameState] =
    IfGameStopped[F].perform {
      DoNothing[F].perform
    } {
      IfGameStarted[F].perform {
        ifCan
      } {
        ifCannot
      }
    }

}
