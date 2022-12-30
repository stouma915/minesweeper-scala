package net.st915.minesweeper.ui.components

import cats.Monad
import cats.effect.IO
import net.st915.minesweeper.event.{CellClickEvent, CellRightClickEvent}
import net.st915.minesweeper.idfactory.CanCreateID
import net.st915.minesweeper.{Coordinate, EventQueue, RunContext}
import net.st915.typesafescalajs.elements.*
import net.st915.typesafescalajs.elements.properties.*
import net.st915.typesafescalajs.nodes.properties.InnerText

object GameScreen {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*
  import cats.syntax.traverse.*
  import net.st915.typesafescalajs.syntax.appendNodeSyntax.*

  import net.st915.minesweeper.idfactory.instances.all.given

  type Cell
  type FlagIcon
  type FlagPlaceholderIcon
  type MineIcon
  type MineCountContainer

  private def flagContainer[F[_]: Monad](coord: Coordinate): F[Div] =
    CanCreateID[F, FlagIcon](coord) >>= { containerID =>
      Monad[F].pure {
        Div(className = ClassName("iconContainer"), id = containerID) with_ {
          Div(className = ClassName("flag")) with_ (
            Div(className = ClassName("flagPart flagTop")),
            Div(className = ClassName("flagPart flagMiddle")),
            Div(className = ClassName("flagPart flagBottom"))
          )
        }
      }
    }

  private def flagPlaceholderContainer[F[_]: Monad](coord: Coordinate): F[Div] =
    CanCreateID[F, FlagPlaceholderIcon](coord) >>= { containerID =>
      Monad[F].pure {
        Div(className = ClassName("iconContainer"), id = containerID) with_ {
          Div(className = ClassName("flagPlaceholder")) with_ (
            Div(className = ClassName("flagPlaceholderPart flagPlaceholderTop")),
            Div(className = ClassName("flagPlaceholderPart flagPlaceholderMiddle")),
            Div(className = ClassName("flagPlaceholderPart flagPlaceholderBottom"))
          )
        }
      }
    }

  private def mineContainer[F[_]: Monad](coord: Coordinate): F[Div] =
    CanCreateID[F, MineIcon](coord) >>= { containerID =>
      Monad[F].pure {
        Div(className = ClassName("iconContainer"), id = containerID) with_ {
          Div(className = ClassName("mine")) with_ (
            Div(className = ClassName("minePart mineTop")),
            Div(className = ClassName("minePart mineMiddleLeft")),
            Div(className = ClassName("minePart mineMiddleCenter"))
          )
        }
      }
    }

  private def mineCountContainer[F[_]: Monad](coord: Coordinate): F[Div] =
    CanCreateID[F, MineCountContainer](coord) >>= { containerID =>
      Monad[F].pure {
        Div(className = ClassName("mineCountContainer"), id = containerID)
      }
    }

  private def cell[F[_]: Monad](coord: Coordinate): F[Div] =
    for {
      flagIcon <- flagContainer[F](coord)
      flagPlaceholderIcon <- flagPlaceholderContainer[F](coord)
      mineIcon <- mineContainer[F](coord)
      mineCount <- mineCountContainer[F](coord)
      containerID <- CanCreateID[F, Cell](coord)
    } yield {
      Div(
        className = ClassName("cell cellNotOpened"),
        id = containerID,
        clickEvent = ClickEvent(EventQueue.queue[IO](CellClickEvent(coord))),
        rightClickEvent = RightClickEvent(EventQueue.queue[IO](CellRightClickEvent(coord)))
      ) with_ (
        flagIcon,
        flagPlaceholderIcon,
        mineIcon,
        mineCount
      )
    }

  private def cellLine[F[_]: Monad](y: Int)(using RunContext): F[Div] =
    (0 until summon[RunContext].difficulty.width)
      .toList
      .map { x => cell[F](Coordinate(x, y)) }
      .sequence >>= { cells => Monad[F].pure(Div(className = ClassName("line")) with_ cells) }

  private def cellArray[F[_]: Monad](using RunContext): F[Div] =
    (0 until summon[RunContext].difficulty.height)
      .toList
      .map(cellLine[F])
      .sequence >>= { cells => Monad[F].pure(Div(className = ClassName("cellArray")) with_ cells) }

  private def flagButton[F[_]: Monad]: F[Div] =
    Button.component[F](
      containerID = ID("toggleFlagPlaceMode"),
      text = InnerText("Enter Flag Place Mode")
    )

  private def restartButton[F[_]: Monad]: F[Div] =
    Button.component[F](
      containerID = ID("restart"),
      text = InnerText("Restart")
    )

  def component[F[_]: Monad](using RunContext): F[Div] =
    for {
      cellArrayComponent <- cellArray[F]
      flagButtonComponent <- flagButton[F]
      restartButtonComponent <- restartButton[F]
    } yield {
      Div(className = ClassName("gameScreen")) with_ (
        cellArrayComponent,
        BR(),
        flagButtonComponent,
        BR(),
        restartButtonComponent,
        BR()
      )
    }

}
