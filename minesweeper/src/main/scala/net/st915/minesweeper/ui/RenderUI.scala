package net.st915.minesweeper.ui

import cats.effect.Sync
import cats.effect.unsafe.IORuntime
import net.st915.immutablescalajs.*
import net.st915.immutablescalajs.dom.*
import net.st915.immutablescalajs.util.CanAppendToDocument
import net.st915.minesweeper.RunContext
import net.st915.minesweeper.ui.components.*

object RenderUI {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  import net.st915.immutablescalajs.util.instances.all.given

  def wired[F[_]: Sync](using ScalaJSDocument, ScalaJSWindow, IORuntime, RunContext): F[Unit] =
    for {
      informationText <- InformationText.wired[F]
      _ <- CanAppendToDocument[F, HTMLDivElement](informationText)

      gameScreen <- GameScreen.wired[F]
      _ <- CanAppendToDocument[F, ScalaJSElement](gameScreen)

      diffSelector <- DifficultySelector.wired[F]
      _ <- CanAppendToDocument[F, ScalaJSElement](diffSelector)

      aboutPage <- AboutPage.wired[F]
      _ <- CanAppendToDocument[F, HTMLDivElement](aboutPage)
    } yield ()

}
