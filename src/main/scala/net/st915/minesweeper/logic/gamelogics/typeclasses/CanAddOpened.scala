package net.st915.minesweeper.logic.gamelogics.typeclasses

import net.st915.minesweeper.{Coordinate, GameState}

object CanAddOpened {

  def apply[F[_]](using ev: CanAddOpened[F]): CanAddOpened[F] = ev

}

trait CanAddOpened[F[_]] {

  def perform(coord: Coordinate)(using GameState): F[GameState]

}
