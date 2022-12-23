package net.st915.minesweeper.ui.components

import cats.Monad
import cats.effect.{IO, Sync}
import net.st915.immutablescalajs.Components
import net.st915.immutablescalajs.componentcreators.*
import net.st915.immutablescalajs.dom.properties.*
import net.st915.minesweeper.event.*
import net.st915.minesweeper.idfactory.CanCreateID
import net.st915.minesweeper.{Coordinate, EventQueue, RunContext}

object GameScreen {

  import cats.syntax.flatMap.*
  import cats.syntax.traverse.*
  import net.st915.immutablescalajs.dom.syntax.propertySyntax.*

  import net.st915.immutablescalajs.componentcreators.instances.all.given
  import net.st915.minesweeper.idfactory.instances.all.given

  import net.st915.immutablescalajs.dom.typealiases.*

  type Cell
  type FlagIcon
  type FlagPlaceholderIcon
  type MineIcon
  type MineCountContainer

  def containerDiv[F[_]: Monad]: F[Div] =
    CanCreateElement[F, Div]() >>=
      CanSetCSSClass[F, Div]("gameScreen".asCSSClass)

  def flagIcon[F[_]: Monad]: F[Div] =
    CanCreateElement[F, Div]() >>=
      CanSetCSSClass[F, Div]("flag".asCSSClass) >>=
      CanAppendChild[F, Div] {
        CanCreateElement[F, Div]() >>=
          CanSetCSSClass[F, Div]("flagPart flagTop".asCSSClass)
      } >>=
      CanAppendChild[F, Div] {
        CanCreateElement[F, Div]() >>=
          CanSetCSSClass[F, Div]("flagPart flagMiddle".asCSSClass)
      } >>=
      CanAppendChild[F, Div] {
        CanCreateElement[F, Div]() >>=
          CanSetCSSClass[F, Div]("flagPart flagBottom".asCSSClass)
      }

  def flagPlaceholderIcon[F[_]: Monad]: F[Div] =
    CanCreateElement[F, Div]() >>=
      CanSetCSSClass[F, Div]("flagPlaceholder".asCSSClass) >>=
      CanAppendChild[F, Div] {
        CanCreateElement[F, Div]() >>=
          CanSetCSSClass[F, Div]("flagPlaceholderPart flagPlaceholderTop".asCSSClass)
      } >>=
      CanAppendChild[F, Div] {
        CanCreateElement[F, Div]() >>=
          CanSetCSSClass[F, Div]("flagPlaceholderPart flagPlaceholderMiddle".asCSSClass)
      } >>=
      CanAppendChild[F, Div] {
        CanCreateElement[F, Div]() >>=
          CanSetCSSClass[F, Div]("flagPlaceholderPart flagPlaceholderBottom".asCSSClass)
      }

  def mineIcon[F[_]: Monad]: F[Div] =
    CanCreateElement[F, Div]() >>=
      CanSetCSSClass[F, Div]("mine".asCSSClass) >>=
      CanAppendChild[F, Div] {
        CanCreateElement[F, Div]() >>=
          CanSetCSSClass[F, Div]("minePart mineTop".asCSSClass)
      } >>=
      CanAppendChild[F, Div] {
        CanCreateElement[F, Div]() >>=
          CanSetCSSClass[F, Div]("minePart mineMiddleLeft".asCSSClass)
      } >>=
      CanAppendChild[F, Div] {
        CanCreateElement[F, Div]() >>=
          CanSetCSSClass[F, Div]("minePart mineMiddleCenter".asCSSClass)
      }

  def iconContainer[F[_]: Monad]: F[Div] =
    CanCreateElement[F, Div]() >>=
      CanSetCSSClass[F, Div]("iconContainer".asCSSClass)

  def flagContainer[F[_]: Monad](coord: Coordinate): F[Div] =
    CanCreateID[F, FlagIcon](coord) >>= { id =>
      iconContainer >>=
        CanSetID[F, Div](id) >>=
        CanAppendChild[F, Div](flagIcon)
    }

  def flagPlaceholderContainer[F[_]: Monad](coord: Coordinate): F[Div] =
    CanCreateID[F, FlagPlaceholderIcon](coord) >>= { id =>
      iconContainer >>=
        CanSetID[F, Div](id) >>=
        CanAppendChild[F, Div](flagPlaceholderIcon)
    }

  def mineContainer[F[_]: Monad](coord: Coordinate): F[Div] =
    CanCreateID[F, MineIcon](coord) >>= { id =>
      iconContainer >>=
        CanSetID[F, Div](id) >>=
        CanAppendChild[F, Div](mineIcon)
    }

  def mineCountContainer[F[_]: Monad](coord: Coordinate): F[Div] =
    CanCreateID[F, MineCountContainer](coord) >>= { id =>
      CanCreateElement[F, Div]() >>=
        CanSetCSSClass[F, Div]("mineCountContainer".asCSSClass) >>=
        CanSetID[F, Div](id)
    }

  def cellClickEvent[F[_]: Sync](coord: Coordinate): F[Unit] =
    EventQueue.queue[F](CellClickEvent(coord))

  def cellRightClickEvent[F[_]: Sync](coord: Coordinate): F[Unit] =
    EventQueue.queue[F](CellRightClickEvent(coord))

  def cell[F[_]: Monad](coord: Coordinate): F[Div] =
    CanCreateID[F, Cell](coord) >>= { id =>
      CanCreateElement[F, Div]() >>=
        CanSetCSSClass[F, Div]("cell cellNotOpened".asCSSClass) >>=
        CanSetID[F, Div](id) >>=
        CanSetClickEvent[F, Div](cellClickEvent[IO](coord)) >>=
        CanSetRightClickEvent[F, Div](cellRightClickEvent[IO](coord)) >>=
        CanAppendChild[F, Div](flagContainer(coord)) >>=
        CanAppendChild[F, Div](flagPlaceholderContainer(coord)) >>=
        CanAppendChild[F, Div](mineContainer(coord)) >>=
        CanAppendChild[F, Div](mineCountContainer(coord))
    }

  def cellLine[F[_]: Monad](y: Int)(using RunContext): F[Div] =
    CanCreateElement[F, Div]() >>=
      CanSetCSSClass[F, Div]("line".asCSSClass) >>=
      CanAppendChilds[F, Div] {
        (0 until summon[RunContext].difficulty.width)
          .toList
          .map { x => cell[F](Coordinate(x, y)) }
          .sequence
      }

  def cellArray[F[_]: Monad](using RunContext): F[Div] =
    CanCreateElement[F, Div]() >>=
      CanSetCSSClass[F, Div]("cellArray".asCSSClass) >>=
      CanAppendChilds[F, Div] {
        (0 until summon[RunContext].difficulty.height)
          .toList
          .map(cellLine[F])
          .sequence
      }

  def flagButton[F[_]: Monad]: F[Div] =
    Button.wired[F]("toggleFlagPlaceMode".asID, "Enter Flag Place Mode".asText)

  def restartButton[F[_]: Monad]: F[Div] =
    Button.wired[F]("restart".asID, "Restart".asText)

  def wired[F[_]: Monad](using RunContext): F[Div] =
    containerDiv >>=
      CanAppendChild[F, Div](cellArray) >>=
      CanAppendChild[F, Div](Components.BR) >>=
      CanAppendChild[F, Div](flagButton) >>=
      CanAppendChild[F, Div](Components.BR) >>=
      CanAppendChild[F, Div](restartButton) >>=
      CanAppendChild[F, Div](Components.BR)

}
