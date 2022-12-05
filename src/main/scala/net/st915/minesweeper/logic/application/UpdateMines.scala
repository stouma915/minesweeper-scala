package net.st915.minesweeper.logic.application

import net.st915.minesweeper.{Coordinate, GameState}

object UpdateMines {

  def apply[F[_]: UpdateMines]: UpdateMines[F] = implicitly

}

trait UpdateMines[F[_]] {

  def update(newMines: List[Coordinate])(implicit gameState: GameState): F[GameState]

}
