package net.st915.minesweeper.ui.components

import cats.Monad
import net.st915.immutablescalajs.componentcreators.*
import net.st915.immutablescalajs.dom.properties.Hyperlink
import net.st915.immutablescalajs.{Components, ScalaJSWindow}
import net.st915.minesweeper.Difficulty

object DifficultySelector {

  import cats.syntax.flatMap.*
  import cats.syntax.traverse.*
  import net.st915.immutablescalajs.dom.syntax.propertySyntax.*

  import net.st915.immutablescalajs.componentcreators.instances.all.given

  import net.st915.immutablescalajs.dom.typealiases.*

  def diffHyperlink[F[_]: Monad](diff: Difficulty)(using ScalaJSWindow): F[Hyperlink] =
    Monad[F].pure {
      val currentURL = new org.scalajs.dom.URL(summon[ScalaJSWindow].location.href)
      val param =
        if (diff eq Difficulty.Default)
          ""
        else
          s"?d=${diff.id}"

      Hyperlink(s"${currentURL.origin}${currentURL.pathname}$param")
    }

  def containerDiv[F[_]: Monad]: F[Div] =
    CanCreateElement[F, Div]() >>=
      CanSetCSSClass[F, Div]("difficultySelector".asCSSClass)

  def descriptionText[F[_]: Monad]: F[Span] =
    CanCreateElement[F, Span]() >>=
      CanSetText[F, Span]("Difficulties:".asText)

  def diffLink[F[_]: Monad](diff: Difficulty)(using ScalaJSWindow): F[Anchor] =
    diffHyperlink(diff) >>= { diffHref =>
      CanCreateElement[F, Anchor]() >>=
        CanSetText[F, Anchor](diff.displayName.asText) >>=
        CanSetHyperlink[F, Anchor](diffHref) >>=
        CanAppendChild[F, Anchor](Components.BR)
    }

  def diffLinks[F[_]: Monad](using ScalaJSWindow): F[List[Anchor]] =
    Difficulty.All.map(diffLink[F]).sequence

  def wired[F[_]: Monad](using ScalaJSWindow): F[Div] =
    containerDiv >>=
      CanAppendChild[F, Div](descriptionText) >>=
      CanAppendChild[F, Div](Components.BR) >>=
      CanAppendChilds[F, Div](diffLinks) >>=
      CanAppendChild[F, Div](Components.BR)

}
