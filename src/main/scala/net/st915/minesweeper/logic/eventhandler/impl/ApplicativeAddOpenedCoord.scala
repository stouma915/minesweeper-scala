package net.st915.minesweeper.logic.eventhandler.impl

import cats.Applicative
import net.st915.minesweeper.{Coordinate, GameState}
import net.st915.minesweeper.logic.eventhandler.application.AddOpenedCoord

class ApplicativeAddOpenedCoord[F[_]: Applicative] extends AddOpenedCoord[F] {

  override def add(coord: Coordinate)(implicit gameState: GameState): F[GameState] =
    Applicative[F].pure(gameState.copy(openedCoord = gameState.openedCoord.appended(coord)))

}
