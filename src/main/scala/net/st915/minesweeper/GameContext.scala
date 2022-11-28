package net.st915.minesweeper

import cats.effect.IO
import net.st915.minesweeper.Coordinate

object GameContext {

  def empty: GameContext = GameContext()

}

case class GameContext(
    var gameStarted: Boolean = false,
    var gameEnded: Boolean = false,
    var opened: List[Coordinate] = List(),
    var mines: List[Coordinate] = List(),
    var flagged: List[Coordinate] = List()
) {

  def init: IO[Unit] = IO {
    gameStarted = false
    gameEnded = false
    opened = List()
    mines = List()
    flagged = List()
  }

}
