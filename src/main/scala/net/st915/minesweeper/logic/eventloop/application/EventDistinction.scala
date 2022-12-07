package net.st915.minesweeper.logic.eventloop.application

import net.st915.minesweeper.event.Event
import net.st915.minesweeper.{Difficulty, GameState}

object EventDistinction {

  def apply[F[_]: EventDistinction]: EventDistinction[F] = implicitly

}

trait EventDistinction[F[_]] {

  def perform(event: Event, gameState: GameState, difficulty: Difficulty): F[GameState]

}
