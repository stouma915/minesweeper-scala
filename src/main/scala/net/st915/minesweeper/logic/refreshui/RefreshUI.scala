package net.st915.minesweeper.logic.refreshui

import cats.effect.Sync
import net.st915.minesweeper.logic.refreshui.task.*
import net.st915.minesweeper.{Difficulty, GameState}
import org.scalajs.dom.*

object RefreshUI {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](difficulty: Difficulty, gameState: GameState)(implicit
  document: HTMLDocument): F[Unit] =
    RefreshUI(difficulty, gameState)

  def apply[F[_]: Sync](difficulty: Difficulty, gameState: GameState)(implicit
  document: HTMLDocument): F[Unit] = {
    implicit val _gameState: GameState = gameState

    for {
      _ <- UpdateButtonText.wired[F]
      _ <- UpdateCellClass.wired[F](difficulty)
    } yield ()
  }

}
