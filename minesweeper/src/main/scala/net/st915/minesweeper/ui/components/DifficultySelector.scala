package net.st915.minesweeper.ui.components

import cats.Monad
import net.st915.minesweeper.Difficulty
import net.st915.typesafescalajs.{ScalaJSURL, ScalaJSWindow}
import net.st915.typesafescalajs.elements.*
import net.st915.typesafescalajs.elements.properties.*
import net.st915.typesafescalajs.nodes.*

object DifficultySelector {

  import cats.syntax.flatMap.*
  import cats.syntax.traverse.*
  import net.st915.typesafescalajs.syntax.appendNodeSyntax.*

  private def diffHyperlink[F[_]: Monad](diff: Difficulty)(using ScalaJSWindow): F[Link] =
    Monad[F].pure {
      val currentURL = new ScalaJSURL(summon[ScalaJSWindow].location.href)
      val param =
        if (diff eq Difficulty.Default)
          ""
        else
          s"?d=${diff.id}"

      Link(s"${currentURL.origin}${currentURL.pathname}$param")
    }

  private def diffAnchor[F[_]: Monad](diff: Difficulty)(using ScalaJSWindow): F[Anchor] =
    diffHyperlink(diff) >>= { diffHref =>
      Monad[F].pure {
        Anchor(href = diffHref) with_ (
          TextNode(diff.displayName),
          BR()
        )
      }
    }

  private def diffAnchors[F[_]: Monad](using ScalaJSWindow): F[List[Anchor]] =
    Difficulty.All.map(diffAnchor).sequence

  def component[F[_]: Monad](using ScalaJSWindow): F[Div] =
    diffAnchors >>= { dAnchors =>
      Monad[F].pure {
        Div(className = ClassName("difficultySelector")) with_ (
          Span() with_ {
            TextNode("Difficulties:")
          },
          BR()
        ) with_ dAnchors with_ BR()
      }
    }

}
