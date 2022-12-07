package net.st915.minesweeper.logic.application

import net.st915.minesweeper.{Coordinate, Difficulty}

object ForAllCoords {

  def apply[F[_]: ForAllCoords]: ForAllCoords[F] = implicitly

}

trait ForAllCoords[F[_]] {

  def perform[A](difficulty: Difficulty)(program: Coordinate => F[A]): F[List[A]]

}