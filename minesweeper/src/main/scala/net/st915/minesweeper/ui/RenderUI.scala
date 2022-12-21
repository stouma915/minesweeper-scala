package net.st915.minesweeper.ui

import cats.effect.Sync
import cats.effect.unsafe.IORuntime
import net.st915.immutablescalajs.*
import net.st915.immutablescalajs.converters.*
import net.st915.immutablescalajs.dom.*
import net.st915.minesweeper.RunContext
import net.st915.minesweeper.ui.components.*
import net.st915.minesweeper.ui.components.instances.SyncCanAppendElement
import net.st915.minesweeper.ui.components.typeclasses.CanAppendElement

object RenderUI {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  import net.st915.immutablescalajs.converters.instances.all.given

  def wired[F[_]: Sync](using ScalaJSDocument, ScalaJSWindow, IORuntime, RunContext): F[Unit] = {
    given CanAppendElement[F] = SyncCanAppendElement[F]

    val body = summon[ScalaJSDocument].body

    for {
      wrappedInformationText <- InformationText.wired[F]
      informationText <-
        CanConvertElement[F, HTMLDivElement, ScalaJSDivElement](wrappedInformationText)
      _ <- CanAppendElement[F].perform(body, informationText)

      gameScreen <- GameScreen.wired[F]
      _ <- CanAppendElement[F].perform(body, gameScreen)

      diffSelector <- DifficultySelector.wired[F]
      _ <- CanAppendElement[F].perform(body, diffSelector)

      wrappedAboutPage <- AboutPage.wired[F]
      aboutPage <-
        CanConvertElement[F, HTMLDivElement, ScalaJSDivElement](wrappedAboutPage)
      _ <- CanAppendElement[F].perform(body, aboutPage)
    } yield ()
  }

}
