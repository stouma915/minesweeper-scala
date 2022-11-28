package net.st915.minesweeper.component

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.event.*
import net.st915.minesweeper.implicits.*
import net.st915.minesweeper.{Constants, Coordinate, Difficulty}
import org.scalajs.dom.*

import scala.util.chaining.*

object GameScreen {

  def make(
      difficulty: Difficulty
  )(implicit doc: Document, runtime: IORuntime): IO[Element] =
    for {
      cellList <- CellList.make(
        difficulty,
        coord =>
          for {
            event <- IO(CellClickEvent(coord))
            _ <- EventQueue.queue(event)
          } yield (),
        coord =>
          for {
            event <- IO(CellRightClickEvent(coord))
            _ <- EventQueue.queue(event)
          } yield ()
      )
      flagPlaceButton <- Button.make(
        Constants.NotFlagPlaceModeText,
        Constants.FlagPlaceButtonId,
        for {
          event <- IO(ButtonClickEvent(Constants.FlagPlaceButtonId))
          _ <- EventQueue.queue(event)
        } yield ()
      )
      restartButton <- Button.make(
        "Restart",
        Constants.RestartButtonId,
        for {
          event <- IO(ButtonClickEvent(Constants.RestartButtonId))
          _ <- EventQueue.queue(event)
        } yield ()
      )
      component <- IO {
        doc
          .createElement("div")
          .tap(_.classList.add("gameScreen"))
          .tap(_.appendChild(cellList))
          .tap(_.appendChild(doc.makeBR))
          .tap(_.appendChild(flagPlaceButton))
          .tap(_.appendChild(doc.makeBR))
          .tap(_.appendChild(restartButton))
          .tap(_.appendChild(doc.makeBR))
      }
    } yield component

}
