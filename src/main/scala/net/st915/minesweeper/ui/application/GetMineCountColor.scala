package net.st915.minesweeper.ui.application

object GetMineCountColor {

  def apply[F[_]: GetMineCountColor]: GetMineCountColor[F] = implicitly

}

trait GetMineCountColor[F[_]] {

  def get(num: Int): F[String]

}
