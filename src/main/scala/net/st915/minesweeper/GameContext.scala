package net.st915.minesweeper

import net.st915.minesweeper.Coordinate

object GameContext {

  def empty: GameContext = GameContext()

}

case class GameContext(
    gameStarted: Boolean = false,
    gameEnded: Boolean = false,
    opened: List[Coordinate] = List(),
    mines: List[Coordinate] = List(),
    flagged: List[Coordinate] = List()
) {

  def updateGameStarted(bool: Boolean): GameContext =
    GameContext(
      bool,
      gameEnded,
      opened,
      mines,
      flagged
    )

  def updateGameEnded(bool: Boolean): GameContext =
    GameContext(
      gameStarted,
      bool,
      opened,
      mines,
      flagged
    )

  def updateOpened(list: List[Coordinate]): GameContext =
    GameContext(
      gameStarted,
      gameEnded,
      list,
      mines,
      flagged
    )

  def updateMines(list: List[Coordinate]): GameContext =
    GameContext(
      gameStarted,
      gameEnded,
      opened,
      list,
      flagged
    )

  def updateFlagged(list: List[Coordinate]): GameContext =
    GameContext(
      gameStarted,
      gameEnded,
      opened,
      mines,
      list
    )

}
