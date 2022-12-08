package net.st915.minesweeper.beforeui.tasks

import cats.effect.Sync
import net.st915.minesweeper.Difficulty
import net.st915.minesweeper.beforeui.tasks.typeclasses.*
import net.st915.minesweeper.beforeui.tasks.instances.*
import org.scalajs.dom.*

object GetDifficulty {

  import cats.syntax.flatMap.*

  def wired[F[_]: Sync](using Window): F[Difficulty] = {
    given CanGetDifficultyFromOption[F] = MonadCanGetDifficultyFromOption[F]
    given CanGetDifficultyParameter[F] = SyncCanGetDifficultyParameter[F]

    CanGetDifficultyParameter[F].get >>=
      CanGetDifficultyFromOption[F].get
  }

}
