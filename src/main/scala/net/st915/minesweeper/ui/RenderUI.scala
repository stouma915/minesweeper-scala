package net.st915.minesweeper.ui

import cats.effect.Sync
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.RunContext
import net.st915.minesweeper.ui.components.*
import net.st915.minesweeper.ui.components.instances.*
import net.st915.minesweeper.ui.components.typeclasses.*
import org.scalajs.dom.*

object RenderUI {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](runContext: RunContext)(
    using HTMLDocument,
    Window,
    IORuntime
  ): F[Unit] = {
    given CanAppendBR[F] = SyncCanAppendBR[F]
    given CanAppendElement[F] = SyncCanAppendElement[F]

    val body = summon[HTMLDocument].body

    for {
      informationText <- InformationText.wired[F]
      _ <- CanAppendElement[F].perform(body, informationText)

      _ <- CanAppendBR[F].perform(body)

      gameScreen <- GameScreen.wired[F](runContext.difficulty)
      _ <- CanAppendElement[F].perform(body, gameScreen)

      _ <- CanAppendBR[F].perform(body)

      diffSelector <- DifficultySelector.wired[F]
      _ <- CanAppendElement[F].perform(body, diffSelector)

      _ <- CanAppendBR[F].perform(body)

      aboutPage <- AboutPage.wired[F]
      _ <- CanAppendElement[F].perform(body, aboutPage)
    } yield ()
  }

}
