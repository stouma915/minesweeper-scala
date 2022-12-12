package net.st915.minesweeper.logic.instances

import cats.Monad
import net.st915.minesweeper.{Coordinate, GameState}
import net.st915.minesweeper.logic.typeclasses.IsFlagged

class MonadIsFlagged[F[_]: Monad] extends IsFlagged[F] {

  override def check(coord: Coordinate)(using gameState: GameState): F[Boolean] =
    Monad[F].pure {
      gameState.flaggedCoords.contains(coord)
    }

}
