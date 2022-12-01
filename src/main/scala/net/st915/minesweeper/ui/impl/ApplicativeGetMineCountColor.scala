package net.st915.minesweeper.ui.impl

import cats.Applicative
import net.st915.minesweeper.ui.application.GetMineCountColor

class ApplicativeGetMineCountColor[F[_]: Applicative] extends GetMineCountColor[F] {

  override def get(num: Int): F[String] =
    Applicative[F].pure {
      num match {
        case 1 => "#0200f9"
        case 2 => "#008001"
        case 3 => "#fd0100"
        case 4 => "#010080"
        case 5 => "#800002"
        case 6 => "#00817c"
        case 7 => "#000000"
        case 8 => "#808080"
        case _ => "transparent"
      }
    }

}
