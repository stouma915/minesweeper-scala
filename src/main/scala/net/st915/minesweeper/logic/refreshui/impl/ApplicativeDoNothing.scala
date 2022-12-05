package net.st915.minesweeper.logic.refreshui.impl

import cats.Applicative
import net.st915.minesweeper.logic.refreshui.application.DoNothing

class ApplicativeDoNothing[F[_]: Applicative] extends DoNothing[F] {

  override def perform: F[Unit] = Applicative[F].unit

}
