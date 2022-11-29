package net.st915.minesweeper.logic

import cats.effect.IO
import net.st915.minesweeper.{Coordinate, Difficulty, GameContext, Util}
import net.st915.minesweeper.implicits.*

import scala.annotation.tailrec
import scala.util.Random

object MineLogic {

  def generate(
      startPoint: Coordinate,
      difficulty: Difficulty
  ): IO[List[Coordinate]] = IO {
    val blacklist = Util.get3x3(startPoint, difficulty)

    @tailrec
    def _generate(mines: List[Coordinate]): List[Coordinate] = {
      if (mines.length eq difficulty.numOfMines) mines
      else {
        val xCandidate = Random().nextInt(difficulty.width)
        val yCandidate = Random().nextInt(difficulty.height)
        val coordCandidate = Coordinate(xCandidate, yCandidate)

        val inBlacklist = blacklist.contains(coordCandidate)
        val inMines = mines.contains(coordCandidate)

        _generate(
          if (!inBlacklist && !inMines)
            mines.appended(coordCandidate)
          else
            mines
        )
      }
    }

    _generate(List())
  }

  def calcMineCount(
      context: GameContext,
      coord: Coordinate,
      difficulty: Difficulty
  ): Int =
    Util
      .get3x3(coord, difficulty)
      .map(context.isMine)
      .count(x => x)

}
