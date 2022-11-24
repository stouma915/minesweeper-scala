package net.st915.minesweeper

import net.st915.minesweeper.Coordinate

object GameContext {

  def empty: GameContext = GameContext(
    false,
    false,
    List()
  )

}

case class GameContext(
  gameStarted: Boolean,
  gameEnded: Boolean,
  opened: List[Coordinate]
) {

  def updateGameStarted(bool: Boolean): GameContext =
    GameContext(
      bool,
      gameEnded,
      opened
    )

  def updateGameEnded(bool: Boolean): GameContext =
    GameContext(
      gameStarted,
      bool,
      opened
    )

  def updateOpened(list: List[Coordinate]): GameContext =
    GameContext(
      gameStarted,
      gameEnded,
      list
    )

}
