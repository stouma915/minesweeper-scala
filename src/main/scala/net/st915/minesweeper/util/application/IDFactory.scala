package net.st915.minesweeper.util.application

import net.st915.minesweeper.Coordinate

object IDFactory {

  def apply[F[_]: IDFactory]: IDFactory[F] = implicitly

}

trait IDFactory[F[_]] {

  def cell(coord: Coordinate): F[String]

  def iconContainer(iconClass: String, coord: Coordinate): F[String]

  def mineCountContainer(num: Int, coord: Coordinate): F[String]

}
