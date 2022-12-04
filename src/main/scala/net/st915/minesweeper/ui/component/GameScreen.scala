package net.st915.minesweeper.ui.component

import cats.effect.unsafe.IORuntime
import cats.effect.{IO, Sync}
import net.st915.minesweeper.Consts.CSSClass
import net.st915.minesweeper.event.ButtonClickEvent
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.impl.*
import net.st915.minesweeper.{Coordinate, Difficulty, EventQueue}
import org.scalajs.dom.*

object GameScreen {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](difficulty: Difficulty)(
    implicit document: HTMLDocument,
    runtime: IORuntime
  ): F[HTMLDivElement] = {
    implicit val _createElement: CreateElement[F] = SyncCreateElement[F]
    implicit val _createBR: CreateBR[F] = SyncCreateBR[F]
    implicit val _appendElement: AppendElement[F] = SyncAppendElement[F]
    implicit val _appendBR: AppendBR[F] = SyncAppendBR[F]
    implicit val _createDiv: CreateDiv[F] = SyncCreateDiv[F]
    implicit val _updateHTMLClass: UpdateHTMLClass[F] = SyncUpdateHTMLClass[F]

    GameScreen(difficulty)
  }

  def apply[
    F[_]: Sync: AppendBR: AppendElement: CreateDiv: UpdateHTMLClass
  ](difficulty: Difficulty)(
    implicit document: HTMLDocument,
    runtime: IORuntime
  ): F[HTMLDivElement] =
    for {
      gameScreen <- CreateDiv[F].create
      _ <- UpdateHTMLClass[F].update(gameScreen, CSSClass.GameScreen)
      cellArray <- CellArray.wired[F](difficulty)
      _ <- AppendElement[F].append(gameScreen, cellArray)
      _ <- AppendBR[F].append(gameScreen)
      toggleFlagPlaceModeButton <- ToggleFlagModeButton.wired[F]
      _ <- AppendElement[F].append(gameScreen, toggleFlagPlaceModeButton)
      _ <- AppendBR[F].append(gameScreen)
      restartButton <- RestartButton.wired[F]
      _ <- AppendElement[F].append(gameScreen, restartButton)
      _ <- AppendBR[F].append(gameScreen)
    } yield gameScreen

}
