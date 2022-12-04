package net.st915.minesweeper.ui

import cats.effect.Sync
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.Difficulty
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.component.*
import net.st915.minesweeper.ui.impl.*
import org.scalajs.dom.*

object RenderUI {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](difficulty: Difficulty)(
    implicit document: HTMLDocument,
    window: Window,
    runtime: IORuntime
  ): F[Unit] = {
    implicit val _appendElement: AppendElement[F] = SyncAppendElement[F]

    RenderUI(difficulty)
  }

  def apply[F[_]: Sync: AppendElement](difficulty: Difficulty)(
    implicit document: HTMLDocument,
    window: Window,
    runtime: IORuntime
  ): F[Unit] =
    for {
      informationText <- InformationText.wired[F]
      gameScreen <- GameScreen.wired[F](difficulty)
      difficultySelector <- DifficultySelector.wired[F]
      aboutPage <- AboutPage.wired[F]
      _ <- AppendElement[F].append(document.body, informationText)
      _ <- AppendElement[F].append(document.body, gameScreen)
      _ <- AppendElement[F].append(document.body, difficultySelector)
      _ <- AppendElement[F].append(document.body, aboutPage)
    } yield ()

}
