package net.st915.minesweeper.logic.refreshui.impl

import cats.effect.Sync
import net.st915.minesweeper.{Coordinate, Difficulty}
import net.st915.minesweeper.logic.refreshui.application.ForAllCoords
import org.scalajs.dom.*

class SyncForAllCoords[F[_]: Sync] extends ForAllCoords[F] {

  import cats.syntax.traverse.*

  override def perform(difficulty: Difficulty, program: Coordinate => F[Unit])(implicit document: HTMLDocument): F[List[Unit]] =
    (0 until difficulty.width).toList.map { x =>
      (0 until difficulty.height).toList.map { y =>
        program(Coordinate(x, y))
      }
    }.flatten.sequence
}
