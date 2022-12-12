package net.st915.minesweeper.logic.gamelogics.instances

import cats.Monad
import net.st915.minesweeper.logic.gamelogics.typeclasses.CanAddOpened
import net.st915.minesweeper.{Coordinate, GameState}

class MonadCanAddOpened[F[_]: Monad] extends CanAddOpened[F] {

  override def perform(coord: Coordinate)(using gameState: GameState): F[GameState] =
    Monad[F].pure {
      gameState.copy(openedCoords = gameState.openedCoords.appended(coord))
    }

}
