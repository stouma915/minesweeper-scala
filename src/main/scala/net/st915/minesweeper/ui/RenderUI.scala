package net.st915.minesweeper.ui

import cats.effect.Sync
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.RunContext
import net.st915.minesweeper.ui.components.InformationText
import net.st915.minesweeper.ui.components.typeclasses.CanAppendElement
import net.st915.minesweeper.ui.components.instances.SyncCanAppendElement
import org.scalajs.dom.*

object RenderUI {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](runContext: RunContext)(
    using HTMLDocument,
    Window,
    IORuntime
  ): F[Unit] = {
    given CanAppendElement[F] = SyncCanAppendElement[F]

    val body = summon[HTMLDocument].body

    for {
      informationText <- InformationText.wired[F]
      _ <- CanAppendElement[F].perform(body, informationText)
    } yield ()
  }

}
