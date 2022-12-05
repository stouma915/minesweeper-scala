package net.st915.minesweeper.util.impl

import cats.effect.Sync
import net.st915.minesweeper.{Coordinate, Difficulty}
import net.st915.minesweeper.util.application.MineGenerator

import scala.annotation.tailrec
import scala.util.Random

class SyncMineGenerator[F[_]: Sync] extends MineGenerator[F] {

  override def perform(startPoint: Coordinate, difficulty: Difficulty): F[List[Coordinate]] =
    Sync[F].blocking {
      val cannotBePlaced = List(startPoint) // TODO

      @tailrec
      def generate(mines: List[Coordinate]): List[Coordinate] = {
        if (mines.length eq difficulty.numOfMines) mines
        else {
          val xCandidate = Random().nextInt(difficulty.width)
          val yCandidate = Random().nextInt(difficulty.height)
          val coordCandidate = Coordinate(xCandidate, yCandidate)

          val inCannotBePlaced = cannotBePlaced.contains(coordCandidate)
          val alreadyPlaced = mines.contains(coordCandidate)

          generate(
            if (!inCannotBePlaced && !alreadyPlaced)
              mines.appended(coordCandidate)
            else
              mines
          )
        }
      }

      generate(List())
    }

}
