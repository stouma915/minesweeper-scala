package net.st915.minesweeper.logic.gamelogics.instances

import cats.Monad
import net.st915.minesweeper.{Coordinate, GameState}
import net.st915.minesweeper.logic.gamelogics.typeclasses.CanRemoveFlagged

class MonadCanRemoveFlagged[F[_]: Monad] extends CanRemoveFlagged[F] {

  override def perform(coord: Coordinate)(using gameState: GameState): F[GameState] =
    Monad[F].pure {
      gameState.copy(flaggedCoords = gameState.flaggedCoords.filter(_ != coord))
    }

}
