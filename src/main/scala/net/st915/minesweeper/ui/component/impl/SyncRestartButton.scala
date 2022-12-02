package net.st915.minesweeper.ui.component.impl

import cats.effect.{IO, Sync}
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.Consts
import net.st915.minesweeper.event.{ButtonClickEvent, EventQueue}
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.component.application.RestartButton
import org.scalajs.dom.*

class SyncRestartButton[F[_]: Sync: CreateButton] extends RestartButton[F] {

  override def create(implicit document: HTMLDocument, runtime: IORuntime): F[HTMLDivElement] =
    CreateButton[F].create(
      "Restart",
      Consts.RestartButtonId,
      EventQueue.queue[IO](ButtonClickEvent(Consts.RestartButtonId))
    )

}
