package net.st915.minesweeper.logic.instances

import cats.Monad
import net.st915.minesweeper.{Coordinate, GameState}
import net.st915.minesweeper.logic.typeclasses.IsOpened

class MonadIsOpened[F[_]: Monad] extends IsOpened[F] {

  override def check(coord: Coordinate)(using gameState: GameState): F[Boolean] =
    Monad[F].pure {
      gameState.openedCoords.contains(coord)
    }

}
