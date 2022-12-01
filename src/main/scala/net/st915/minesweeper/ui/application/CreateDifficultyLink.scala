package net.st915.minesweeper.ui.application

import net.st915.minesweeper.Difficulty
import org.scalajs.dom.*

object CreateDifficultyLink {

  def apply[F[_]: CreateDifficultyLink]: CreateDifficultyLink[F] = implicitly

}

trait CreateDifficultyLink[F[_]] {

  def create(diff: Difficulty)(implicit window: Window): F[String]

}
