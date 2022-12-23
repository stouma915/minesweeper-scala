package net.st915.minesweeper.ui.components

import cats.Monad
import net.st915.immutablescalajs.Components
import net.st915.immutablescalajs.componentcreators.*

object AboutPage {

  import cats.syntax.flatMap.*
  import net.st915.immutablescalajs.dom.syntax.propertySyntax.*

  import net.st915.immutablescalajs.componentcreators.instances.all.given

  import net.st915.immutablescalajs.dom.typealiases.*

  def containerDiv[F[_]: Monad]: F[Div] =
    CanCreateElement[F, Div]() >>=
      CanSetCSSClass[F, Div]("aboutPage".asCSSClass)

  def thisSiteIsLicensedUnderThe[F[_]: Monad]: F[Span] =
    CanCreateElement[F, Span]() >>=
      CanSetText[F, Span]("This site is licensed under the ".asText)

  def mitLicense[F[_]: Monad]: F[Anchor] =
    CanCreateElement[F, Anchor]() >>=
      CanSetText[F, Anchor]("MIT License".asText) >>=
      CanSetHyperlink[F, Anchor](
        "https://github.com/stouma915/minesweeper-scala/blob/main/LICENSE".asHyperlink
      )

  def thisSiteIsOpenSource[F[_]: Monad]: F[Span] =
    CanCreateElement[F, Span]() >>=
      CanSetText[F, Span]("This site is open source. ".asText)

  def improveThisSite[F[_]: Monad]: F[Anchor] =
    CanCreateElement[F, Anchor]() >>=
      CanSetText[F, Anchor]("Improve this site".asText) >>=
      CanSetHyperlink[F, Anchor](
        "https://github.com/stouma915/minesweeper-scala".asHyperlink
      )

  def poweredBy[F[_]: Monad]: F[Span] =
    CanCreateElement[F, Span]() >>=
      CanSetText[F, Span]("Powered by ".asText)

  def githubPages[F[_]: Monad]: F[Anchor] =
    CanCreateElement[F, Anchor]() >>=
      CanSetText[F, Anchor]("GitHub Pages".asText) >>=
      CanSetHyperlink[F, Anchor]("https://pages.github.com".asHyperlink)

  def period[F[_]: Monad]: F[Span] =
    CanCreateElement[F, Span]() >>=
      CanSetText[F, Span](".".asText)

  def wired[F[_]: Monad]: F[Div] =
    containerDiv >>=
      CanAppendChild[F, Div](thisSiteIsLicensedUnderThe) >>=
      CanAppendChild[F, Div](mitLicense) >>=
      CanAppendChild[F, Div](period) >>=
      CanAppendChild[F, Div](Components.BR) >>=
      CanAppendChild[F, Div](thisSiteIsOpenSource) >>=
      CanAppendChild[F, Div](improveThisSite) >>=
      CanAppendChild[F, Div](period) >>=
      CanAppendChild[F, Div](Components.BR) >>=
      CanAppendChild[F, Div](poweredBy) >>=
      CanAppendChild[F, Div](githubPages) >>=
      CanAppendChild[F, Div](period) >>=
      CanAppendChild[F, Div](Components.BR)

}
