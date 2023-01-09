package net.st915.minesweeper

import cats.{Eq, Monoid}

object Coordinate {

  given coordinateEq: Eq[Coordinate] with
    override def eqv(x: Coordinate, y: Coordinate): Boolean =
      (x.x eq y.x) && (x.y eq y.y)

  given coordinateMonoid: Monoid[Coordinate] with
    override def combine(x: Coordinate, y: Coordinate): Coordinate =
      Coordinate(x.x + y.x, x.y + y.y)

    override def empty: Coordinate =
      Coordinate(0, 0)

}

case class Coordinate(x: Int, y: Int)
