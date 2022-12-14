package net.st915.minesweeper.ui.components

import cats.effect.Sync
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.ui.components.instances.SyncCanCreateButton
import net.st915.minesweeper.ui.components.typeclasses.CanCreateButton
import net.st915.minesweeper.ui.consts.*
import org.scalajs.dom.*

object RestartButton {

  def wired[F[_]: Sync](using HTMLDocument, IORuntime): F[HTMLDivElement] = {
    given CanCreateButton[F] = SyncCanCreateButton[F]

    CanCreateButton[F].create(
      IDs.RestartButtonId,
      UITexts.RestartButton
    )
  }

}
