package net.st915.minesweeper.ui.components

import cats.effect.unsafe.IORuntime
import cats.effect.Sync
import net.st915.minesweeper.Difficulty
import net.st915.minesweeper.ui.components.instances.*
import net.st915.minesweeper.ui.components.typeclasses.*
import net.st915.minesweeper.ui.consts.*
import org.scalajs.dom.*

object GameScreen {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](diff: Difficulty)(using HTMLDocument, IORuntime): F[HTMLDivElement] = {
    given CanAppendBR[F] = SyncCanAppendBR[F]
    given CanAppendElement[F] = SyncCanAppendElement[F]
    given CanCreateButton[F] = SyncCanCreateButton[F]
    given CanCreateElement[F, HTMLDivElement] = MonadCanCreateElementDiv[F]
    given CanUpdateElementClass[F] = SyncCanUpdateElementClass[F]

    for {
      containerDiv <- CanCreateElement[F, HTMLDivElement].create
      _ <- CanUpdateElementClass[F].perform(containerDiv, CSSClasses.GameScreen)

      cellArray <- CellArray.wired[F](diff)
      _ <- CanAppendElement[F].perform(containerDiv, cellArray)

      _ <- CanAppendBR[F].perform(containerDiv)

      flagButton <- CanCreateButton[F].create(
        IDs.ToggleFlagModeButtonId,
        UITexts.EnterFlagPlaceMode
      )
      _ <- CanAppendElement[F].perform(containerDiv, flagButton)

      _ <- CanAppendBR[F].perform(containerDiv)

      restartButton <- CanCreateButton[F].create(
        IDs.RestartButtonId,
        UITexts.RestartButton
      )
      _ <- CanAppendElement[F].perform(containerDiv, restartButton)
    } yield containerDiv
  }

}
