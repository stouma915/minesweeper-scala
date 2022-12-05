package net.st915.minesweeper.logic.impl

import cats.Applicative
import net.st915.minesweeper.GameState
import net.st915.minesweeper.logic.application.*

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
