package net.st915.minesweeper.logic.typeclasses

import net.st915.minesweeper.{Coordinate, GameState}

object IsOpened {

  def apply[F[_]](using ev: IsOpened[F]): IsOpened[F] = ev

}

trait IsOpened[F[_]] {

  def check(coord: Coordinate)(using GameState): F[Boolean]

}
