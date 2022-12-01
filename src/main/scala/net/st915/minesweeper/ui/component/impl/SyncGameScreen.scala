package net.st915.minesweeper.ui.component.impl

import cats.effect.{IO, Sync}
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.{Coordinate, Difficulty}
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.component.application.*
import org.scalajs.dom.*

class SyncGameScreen[F[
    _
]: Sync: AppendBR: AppendElement: CreateElement: UpdateHTMLClass: CellArray]
    extends GameScreen[F] {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  override def create(
      difficulty: Difficulty
  )(implicit document: HTMLDocument, runtime: IORuntime): F[HTMLDivElement] =
    for {
      gameScreen <- CreateElement[F].create[HTMLDivElement]("div")
      _ <- UpdateHTMLClass[F].update(gameScreen, "gameScreen")
      cellArray <- CellArray[F].create(difficulty)
      _ <- AppendElement[F].append(gameScreen, cellArray)
      _ <- AppendBR[F].append(gameScreen)
    } yield gameScreen

}
