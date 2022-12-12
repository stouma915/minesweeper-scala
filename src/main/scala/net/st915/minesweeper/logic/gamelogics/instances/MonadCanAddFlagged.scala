package net.st915.minesweeper.logic.gamelogics.instances

import cats.Monad
import net.st915.minesweeper.logic.gamelogics.typeclasses.CanAddFlagged
import net.st915.minesweeper.{Coordinate, GameState}

class MonadCanAddFlagged[F[_]: Monad] extends CanAddFlagged[F] {

  override def perform(coord: Coordinate)(using gameState: GameState): F[GameState] =
    Monad[F].pure {
      gameState.copy(flaggedCoords = gameState.flaggedCoords.appended(coord))
    }

}
