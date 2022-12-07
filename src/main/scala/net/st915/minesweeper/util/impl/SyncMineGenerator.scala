package net.st915.minesweeper.util.impl

import cats.effect.Sync
import net.st915.minesweeper.util.application.*
import net.st915.minesweeper.{Coordinate, Difficulty}

import scala.annotation.tailrec
import scala.util.Random

class SyncMineGenerator[F[_]: Sync: Get3x3] extends MineGenerator[F] {

  import cats.syntax.flatMap.*

  override def perform(startPoint: Coordinate, difficulty: Difficulty): F[List[Coordinate]] =
    Get3x3[F].get(startPoint, difficulty) >>= { cannotBePlaced =>
      Sync[F].blocking {
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

}
