package net.st915.minesweeper.ui

import cats.effect.Sync
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.RunContext
import net.st915.minesweeper.ui.components.*
import net.st915.typesafescalajs.{ScalaJSDocument, ScalaJSWindow}
import net.st915.typesafescalajs.translater.*
import net.st915.typesafescalajs.translater.instances.*

object RenderUI {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](using ScalaJSDocument, ScalaJSWindow, IORuntime, RunContext): F[Unit] = {
    given CanTranslate[F] = SyncCanTranslate[F]
    given CanTranslateAndRenderToBody[F] = SyncCanTranslateAndRenderToBody[F]

    for {
      informationText <- InformationText.component[F]
      _ <- CanTranslateAndRenderToBody[F](informationText)

      gameScreen <- GameScreen.component[F]
      _ <- CanTranslateAndRenderToBody[F](gameScreen)

      diffSelector <- DifficultySelector.component[F]
      _ <- CanTranslateAndRenderToBody[F](diffSelector)

      aboutPage <- AboutPage.component[F]
      _ <- CanTranslateAndRenderToBody[F](aboutPage)
    } yield ()
  }

}
