package net.st915.minesweeper.util.typeclasses

import net.st915.minesweeper.Difficulty
import net.st915.minesweeper.ui.consts.Link
import org.scalajs.dom.*

object CanCreateDifficultyLink {

  def apply[F[_]](using ev: CanCreateDifficultyLink[F]): CanCreateDifficultyLink[F] = ev

}

trait CanCreateDifficultyLink[F[_]] {

  def create(diff: Difficulty)(using Window): F[Link]

}
