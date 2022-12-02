package net.st915.minesweeper.logic.eventloop.application

import net.st915.minesweeper.GameState
import net.st915.minesweeper.event.Event

object EventDistinction {

  def apply[F[_]: EventDistinction]: EventDistinction[F] = implicitly

}

trait EventDistinction[F[_]] {

  def perform(event: Event, gameState: GameState): F[GameState]

}
