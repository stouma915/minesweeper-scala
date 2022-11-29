package net.st915.minesweeper.logic

import cats.effect.IO
import net.st915.minesweeper.{Coordinate, Difficulty, GameContext}
import net.st915.minesweeper.implicits.*

import scala.util.Random

object MineLogic {

  def generate(
      startPoint: Coordinate,
      difficulty: Difficulty
  ): IO[List[Coordinate]] = IO {
    val blacklist = List(startPoint) // TODO

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
  ): Int = {
    val x = coord.x
    val y = coord.y
    val targets = Seq(
      Coordinate(x - 1, y - 1),
      Coordinate(x - 1, y),
      Coordinate(x - 1, y + 1),
      Coordinate(x, y - 1),
      Coordinate(x, y),
      Coordinate(x, y + 1),
      Coordinate(x + 1, y - 1),
      Coordinate(x + 1, y),
      Coordinate(x + 1, y + 1)
    )

    targets.map(context.isMine).count(x => x)
  }

}
