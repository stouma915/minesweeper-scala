package net.st915.minesweeper.syntax

import net.st915.minesweeper.{Coordinate, GameContext}

trait GameContextSyntax {

  implicit class GameContextOps(context: GameContext) {

    def addOpened(coord: Coordinate): Unit =
      context.opened = context.opened.appended(coord)

    def addFlagged(coord: Coordinate): Unit =
      context.flagged = context.flagged.appended(coord)

    def removeFlagged(coord: Coordinate): Unit =
      context.flagged = context.flagged.filter(_ != coord)

    def isOpened(coord: Coordinate): Boolean =
      context.opened.contains(coord)

    def isMine(coord: Coordinate): Boolean =
      context.mines.contains(coord)

    def isFlagged(coord: Coordinate): Boolean =
      context.flagged.contains(coord)
  }

}
