package net.st915.minesweeper.logic.refreshui.task

import cats.effect.Sync
import net.st915.minesweeper.Consts.CSSClass
import net.st915.minesweeper.{Difficulty, GameState}
import net.st915.minesweeper.logic.refreshui.application.*
import net.st915.minesweeper.logic.refreshui.impl.*
import net.st915.minesweeper.util.application.IDFactory
import net.st915.minesweeper.util.impl.ApplicativeIDFactory
import org.scalajs.dom.*

object UpdateCellClass {

  import cats.syntax.flatMap.*

  def wired[F[_]: Sync](difficulty: Difficulty)(
    implicit document: HTMLDocument,
    gameState: GameState
  ): F[List[Unit]] = {
    implicit val _forAllCoords: ForAllCoords[F] = SyncForAllCoords[F]
    implicit val _getElement: GetElement[F] = SyncGetElement[F]
    implicit val _updateHTMLClassWithID: UpdateHTMLClassWithID[F] = SyncUpdateHTMLClassWithID[F]

    implicit val _idFactory: IDFactory[F] = ApplicativeIDFactory[F]

    UpdateCellClass(difficulty)
  }

  def apply[F[_]: Sync: ForAllCoords: UpdateHTMLClassWithID: IDFactory](difficulty: Difficulty)(
    implicit document: HTMLDocument,
    gameState: GameState
  ): F[List[Unit]] =
    ForAllCoords[F].perform(
      difficulty,
      coord =>
        IDFactory[F].cell(coord) >>= { id =>
          UpdateHTMLClassWithID[F].update(
            id,
            if (gameState.openedCoord.contains(coord))
              CSSClass.OpenedCell
            else
              CSSClass.NotOpenedCell
          )
        }
    )

}
