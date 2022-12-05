package net.st915.minesweeper.logic.impl

import cats.Applicative
import net.st915.minesweeper.logic.application.UpdateMines
import net.st915.minesweeper.{Coordinate, GameState}

class ApplicativeUpdateMines[F[_]: Applicative] extends UpdateMines[F] {

  override def update(newMines: List[Coordinate])(implicit gameState: GameState): F[GameState] =
    Applicative[F].pure(gameState.copy(mines = newMines))

}
