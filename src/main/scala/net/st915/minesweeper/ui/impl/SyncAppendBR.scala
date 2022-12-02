package net.st915.minesweeper.ui.impl

import cats.effect.Sync
import net.st915.minesweeper.ui.application.{AppendBR, AppendElement, CreateBR}
import org.scalajs.dom.*

class SyncAppendBR[F[_]: Sync: AppendElement: CreateBR] extends AppendBR[F] {

  import cats.syntax.flatMap.*

  override def append[A <: HTMLElement](parent: A)(implicit document: HTMLDocument): F[Unit] =
    CreateBR[F].create >>= { br => AppendElement[F].append(parent, br) }

}
