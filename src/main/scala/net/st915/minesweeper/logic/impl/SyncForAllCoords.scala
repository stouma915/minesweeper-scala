package net.st915.minesweeper.logic.impl

import cats.effect.Sync
import net.st915.minesweeper.logic.application.ForAllCoords
import net.st915.minesweeper.{Coordinate, Difficulty}
import org.scalajs.dom.*

class SyncForAllCoords[F[_]: Sync] extends ForAllCoords[F] {

  import cats.syntax.traverse.*

  override def perform[A](difficulty: Difficulty)(program: Coordinate => F[A]): F[List[A]] =
    (0 until difficulty.width).toList.flatMap { x =>
      (0 until difficulty.height).toList.map { y => program(Coordinate(x, y)) }
    }.sequence
}
