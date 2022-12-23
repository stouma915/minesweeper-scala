package net.st915.minesweeper.ui.components

import cats.Monad
import cats.effect.{IO, Sync}
import net.st915.immutablescalajs.componentcreators.*
import net.st915.immutablescalajs.dom.properties.*
import net.st915.minesweeper.EventQueue
import net.st915.minesweeper.event.ButtonClickEvent

object Button {

  import cats.syntax.flatMap.*
  import net.st915.immutablescalajs.dom.syntax.propertySyntax.*

  import net.st915.immutablescalajs.componentcreators.instances.all.given

  import net.st915.immutablescalajs.dom.typealiases.*

  def buttonClickEvent[F[_]: Sync](id: ID): F[Unit] =
    EventQueue.queue[F](ButtonClickEvent(id))

  def containerDiv[F[_]: Monad](id: ID): F[Div] =
    CanCreateElement[F, Div]() >>=
      CanSetCSSClass[F, Div]("btn".asCSSClass) >>=
      CanSetClickEvent[F, Div](buttonClickEvent[IO](id))

  def buttonText[F[_]: Monad](id: ID, text: Text): F[Span] =
    CanCreateElement[F, Span]() >>=
      CanSetCSSClass[F, Span]("btnText".asCSSClass) >>=
      CanSetID[F, Span](id) >>=
      CanSetText[F, Span](text)

  def wired[F[_]: Monad](id: ID, text: Text): F[Div] =
    containerDiv(id) >>=
      CanAppendChild[F, Div](buttonText(id, text))

}
