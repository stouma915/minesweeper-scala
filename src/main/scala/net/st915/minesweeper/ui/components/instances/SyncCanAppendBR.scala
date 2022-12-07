package net.st915.minesweeper.ui.components.instances

import cats.effect.Sync
import net.st915.minesweeper.ui.components.typeclasses.*
import net.st915.minesweeper.ui.components.instances.*
import org.scalajs.dom.*

class SyncCanAppendBR[F[_]: Sync] extends CanAppendBR[F] {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  override def perform(parent: HTMLElement)(using HTMLDocument): F[Unit] = {
    given CanCreateElement[F, HTMLBRElement] = MonadCanCreateElementBR[F]
    given CanAppendElement[F] = SyncCanAppendElement[F]

    for {
      br <- CanCreateElement[F, HTMLBRElement].create
      _ <- CanAppendElement[F].perform(parent, br)
    } yield ()
  }

}
