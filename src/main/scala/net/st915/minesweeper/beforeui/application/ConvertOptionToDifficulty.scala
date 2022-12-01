package net.st915.minesweeper.beforeui.application

import net.st915.minesweeper.Difficulty

object ConvertOptionToDifficulty {

  def apply[F[_]: ConvertOptionToDifficulty]: ConvertOptionToDifficulty[F] = implicitly

}

trait ConvertOptionToDifficulty[F[_]] {

  def convert(option: Option[String]): F[Difficulty]

}
