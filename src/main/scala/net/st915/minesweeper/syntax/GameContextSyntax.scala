package net.st915.minesweeper.syntax

import net.st915.minesweeper.{Coordinate, GameContext}

trait GameContextSyntax {

  implicit class GameContextOps(context: GameContext) {

    def addOpened(coord: Coordinate): GameContext =
      context.updateOpened(context.opened.appended(coord))

    def addFlagged(coord: Coordinate): GameContext =
      context.updateFlagged(context.flagged.appended(coord))

    def isOpened(coord: Coordinate): Boolean =
      context.opened.contains(coord)

    def isMine(coord: Coordinate): Boolean =
      context.mines.contains(coord)

    def isFlagged(coord: Coordinate): Boolean =
      context.flagged.contains(coord)
  }

}
