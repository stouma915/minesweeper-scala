package net.st915.minesweeper.ui.components

import cats.effect.Sync
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.ui.components.instances.*
import net.st915.minesweeper.ui.components.typeclasses.*
import net.st915.minesweeper.ui.consts.CSSClasses
import net.st915.minesweeper.{Coordinate, RunContext}
import org.scalajs.dom.*

object CellLine {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*
  import cats.syntax.traverse.*

  def wired[F[_]: Sync](y: Int)(using HTMLDocument, IORuntime, RunContext): F[HTMLDivElement] = {
    given CanAppendElement[F] = SyncCanAppendElement[F]
    given CanCreateElement[F, HTMLDivElement] = MonadCanCreateElementDiv[F]
    given CanUpdateElementClass[F] = SyncCanUpdateElementClass[F]

    for {
      containerDiv <- CanCreateElement[F, HTMLDivElement].create
      _ <- CanUpdateElementClass[F].perform(containerDiv, CSSClasses.CellLine)

      cells <- (0 until summon[RunContext].difficulty.width)
        .toList
        .map { x => Cell.wired[F](Coordinate(x, y)) }
        .sequence
      _ <- cells
        .map { cell => CanAppendElement[F].perform(containerDiv, cell) }
        .sequence
    } yield containerDiv
  }

}
