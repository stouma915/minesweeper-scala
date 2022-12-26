package net.st915.minesweeper.ui

import cats.effect.Sync
import cats.effect.unsafe.IORuntime
import net.st915.immutablescalajs.*
import net.st915.immutablescalajs.util.CanAppendToDocument
import net.st915.minesweeper.RunContext
import net.st915.minesweeper.ui.components.*
import net.st915.typesafescalajs.translater.*
import net.st915.typesafescalajs.translater.instances.*

object RenderUI {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  import net.st915.immutablescalajs.util.instances.all.given

  import net.st915.immutablescalajs.dom.typealiases.*

  def wired[F[_]: Sync](using ScalaJSDocument, ScalaJSWindow, IORuntime, RunContext): F[Unit] = {
    given CanTranslate[F] = SyncCanTranslate[F]
    given CanTranslateAndRenderToBody[F] = SyncCanTranslateAndRenderToBody[F]

    for {
      informationText <- InformationText.component[F]
      _ <- CanTranslateAndRenderToBody[F](informationText)

      gameScreen <- GameScreen.wired[F]
      _ <- CanAppendToDocument[F, Div](gameScreen)

      diffSelector <- DifficultySelector.component[F]
      _ <- CanTranslateAndRenderToBody[F](diffSelector)

      aboutPage <- AboutPage.component[F]
      _ <- CanTranslateAndRenderToBody[F](aboutPage)
    } yield ()
  }

}
