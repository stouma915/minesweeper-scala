package net.st915.minesweeper.util.impl

import cats.Applicative
import net.st915.minesweeper.Coordinate
import net.st915.minesweeper.util.application.IDFactory

class ApplicativeIDFactory[F[_]: Applicative] extends IDFactory[F] {

  override def cell(coord: Coordinate): F[String] =
    Applicative[F].pure(s"cell_${coord.x}_${coord.y}")

  override def iconContainer(iconClass: String, coord: Coordinate): F[String] =
    Applicative[F].pure(s"iconContainer_${iconClass}_${coord.x}_${coord.y}")

  override def mineCountContainer(num: Int, coord: Coordinate): F[String] =
    Applicative[F].pure(s"mineCount_${num}_${coord.x}_${coord.y}")

}
