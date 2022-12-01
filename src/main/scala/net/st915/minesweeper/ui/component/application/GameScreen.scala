package net.st915.minesweeper.ui.component.application

import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.Difficulty
import org.scalajs.dom.*

object GameScreen {

  def apply[F[_]: GameScreen]: GameScreen[F] = implicitly

}

trait GameScreen[F[_]] {

  def create(
      difficulty: Difficulty
  )(implicit document: HTMLDocument, runtime: IORuntime): F[HTMLDivElement]

}
