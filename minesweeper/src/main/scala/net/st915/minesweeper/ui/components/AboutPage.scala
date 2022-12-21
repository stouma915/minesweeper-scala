package net.st915.minesweeper.ui.components

import cats.Monad
import net.st915.immutablescalajs.Components.BR
import net.st915.immutablescalajs.componentcreators.*
import net.st915.immutablescalajs.dom.properties.*

object AboutPage {

  import cats.syntax.flatMap.*

  import net.st915.immutablescalajs.componentcreators.instances.all.given

  import net.st915.immutablescalajs.dom.typealiases.*

  def containerDiv[F[_]: Monad]: F[Div] =
    CanCreateElement[F, Div]() >>=
      CanSetCSSClass[F, Div](CSSClass("aboutPage"))

  def thisSiteIsLicensedUnderThe[F[_]: Monad]: F[Span] =
    CanCreateElement[F, Span]() >>=
      CanSetText[F, Span](Text("This site is licensed under the "))

  def mitLicense[F[_]: Monad]: F[Anchor] =
    CanCreateElement[F, Anchor]() >>=
      CanSetText[F, Anchor](Text("MIT License")) >>=
      CanSetHyperlink[F, Anchor](
        Hyperlink("https://github.com/stouma915/minesweeper-scala/blob/main/LICENSE")
      )

  def thisSiteIsOpenSource[F[_]: Monad]: F[Span] =
    CanCreateElement[F, Span]() >>=
      CanSetText[F, Span](Text("This site is open source. "))

  def improveThisSite[F[_]: Monad]: F[Anchor] =
    CanCreateElement[F, Anchor]() >>=
      CanSetText[F, Anchor](Text("Improve this site")) >>=
      CanSetHyperlink[F, Anchor](
        Hyperlink("https://github.com/stouma915/minesweeper-scala")
      )

  def poweredBy[F[_]: Monad]: F[Span] =
    CanCreateElement[F, Span]() >>=
      CanSetText[F, Span](Text("Powered by "))

  def githubPages[F[_]: Monad]: F[Anchor] =
    CanCreateElement[F, Anchor]() >>=
      CanSetText[F, Anchor](Text("GitHub Pages")) >>=
      CanSetHyperlink[F, Anchor](Hyperlink("https://pages.github.com"))

  def period[F[_]: Monad]: F[Span] =
    CanCreateElement[F, Span]() >>=
      CanSetText[F, Span](Text("."))

  def wired[F[_]: Monad]: F[Div] =
    containerDiv >>=
      CanAppendChild[F, Div](thisSiteIsLicensedUnderThe) >>=
      CanAppendChild[F, Div](mitLicense) >>=
      CanAppendChild[F, Div](period) >>=
      CanAppendChild[F, Div](BR) >>=
      CanAppendChild[F, Div](thisSiteIsOpenSource) >>=
      CanAppendChild[F, Div](improveThisSite) >>=
      CanAppendChild[F, Div](period) >>=
      CanAppendChild[F, Div](BR) >>=
      CanAppendChild[F, Div](poweredBy) >>=
      CanAppendChild[F, Div](githubPages) >>=
      CanAppendChild[F, Div](period) >>=
      CanAppendChild[F, Div](BR)

}
