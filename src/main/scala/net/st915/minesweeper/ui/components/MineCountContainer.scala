package net.st915.minesweeper.ui.components

import cats.effect.Sync
import net.st915.minesweeper.Coordinate
import net.st915.minesweeper.ui.components.instances.*
import net.st915.minesweeper.ui.components.typeclasses.*
import net.st915.minesweeper.ui.consts.CSSClasses
import net.st915.minesweeper.util.instances.MonadCanCreateMineCountContainerID
import net.st915.minesweeper.util.typeclasses.CanCreateMineCountContainerID
import org.scalajs.dom.*

object MineCountContainer {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](coord: Coordinate)(using HTMLDocument): F[HTMLDivElement] = {
    given CanCreateElement[F, HTMLDivElement] = MonadCanCreateElementDiv[F]
    given CanUpdateElementClass[F] = SyncCanUpdateElementClass[F]
    given CanUpdateElementID[F] = SyncCanUpdateElementID[F]

    given CanCreateMineCountContainerID[F] = MonadCanCreateMineCountContainerID[F]

    for {
      containerDiv <- CanCreateElement[F, HTMLDivElement].create
      _ <- CanUpdateElementClass[F].perform(containerDiv, CSSClasses.MineCountContainer)

      containerID <- CanCreateMineCountContainerID[F].create(coord)
      _ <- CanUpdateElementID[F].perform(containerDiv, containerID)
    } yield containerDiv
  }

}
