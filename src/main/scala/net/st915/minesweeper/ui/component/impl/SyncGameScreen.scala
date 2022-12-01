package net.st915.minesweeper.ui.component.impl

import cats.effect.unsafe.IORuntime
import cats.effect.{IO, Sync}
import net.st915.minesweeper.event.{ButtonClickEvent, EventQueue}
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.component.application.*
import net.st915.minesweeper.{Coordinate, Difficulty}
import org.scalajs.dom.*

class SyncGameScreen[
  F[_]: Sync: AppendBR: AppendElement: CreateElement: UpdateHTMLClass: Button: CellArray
] extends GameScreen[F] {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  override def create(difficulty: Difficulty)(
    implicit document: HTMLDocument,
    runtime: IORuntime
  ): F[HTMLDivElement] =
    for {
      gameScreen <- CreateElement[F].create[HTMLDivElement]("div")
      _ <- UpdateHTMLClass[F].update(gameScreen, "gameScreen")
      cellArray <- CellArray[F].create(difficulty)
      _ <- AppendElement[F].append(gameScreen, cellArray)
      _ <- AppendBR[F].append(gameScreen)
      toggleFlagPlaceModeButton <- Button[F].create(
        "Enter Flag Place Mode",
        "toggleFlagPlaceMode",
        EventQueue.queue[IO](ButtonClickEvent("toggleFlagPlaceMode"))
      )
      _ <- AppendElement[F].append(gameScreen, toggleFlagPlaceModeButton)
      _ <- AppendBR[F].append(gameScreen)
      restartButton <- Button[F].create(
        "Restart",
        "restart",
        EventQueue.queue[IO](ButtonClickEvent("restart"))
      )
      _ <- AppendElement[F].append(gameScreen, restartButton)
      _ <- AppendBR[F].append(gameScreen)
    } yield gameScreen

}
