package net.st915.minesweeper.ui.components

import cats.Monad
import net.st915.immutablescalajs.Components.BR
import net.st915.immutablescalajs.componentcreators.*
import net.st915.immutablescalajs.dom.*
import net.st915.immutablescalajs.dom.properties.*

object AboutPage {

  import cats.syntax.flatMap.*

  import net.st915.immutablescalajs.componentcreators.instances.all.given

  def containerDiv[F[_]: Monad]: F[HTMLDivElement] =
    CanCreateElement[F, HTMLDivElement]() >>=
      CanSetCSSClass[F, HTMLDivElement](CSSClass("aboutPage"))

  def thisSiteIsLicensedUnderThe[F[_]: Monad]: F[HTMLSpanElement] =
    CanCreateElement[F, HTMLSpanElement]() >>=
      CanSetText[F, HTMLSpanElement](Text("This site is licensed under the "))

  def mitLicense[F[_]: Monad]: F[HTMLAnchorElement] =
    CanCreateElement[F, HTMLAnchorElement]() >>=
      CanSetText[F, HTMLAnchorElement](Text("MIT License")) >>=
      CanSetHyperlink[F, HTMLAnchorElement](
        Hyperlink("https://github.com/stouma915/minesweeper-scala/blob/main/LICENSE")
      )

  def thisSiteIsOpenSource[F[_]: Monad]: F[HTMLSpanElement] =
    CanCreateElement[F, HTMLSpanElement]() >>=
      CanSetText[F, HTMLSpanElement](Text("This site is open source. "))

  def improveThisSite[F[_]: Monad]: F[HTMLAnchorElement] =
    CanCreateElement[F, HTMLAnchorElement]() >>=
      CanSetText[F, HTMLAnchorElement](Text("Improve this site")) >>=
      CanSetHyperlink[F, HTMLAnchorElement](
        Hyperlink("https://github.com/stouma915/minesweeper-scala")
      )

  def poweredBy[F[_]: Monad]: F[HTMLSpanElement] =
    CanCreateElement[F, HTMLSpanElement]() >>=
      CanSetText[F, HTMLSpanElement](Text("Powered by "))

  def githubPages[F[_]: Monad]: F[HTMLAnchorElement] =
    CanCreateElement[F, HTMLAnchorElement]() >>=
      CanSetText[F, HTMLAnchorElement](Text("GitHub Pages")) >>=
      CanSetHyperlink[F, HTMLAnchorElement](Hyperlink("https://pages.github.com"))

  def period[F[_]: Monad]: F[HTMLSpanElement] =
    CanCreateElement[F, HTMLSpanElement]() >>=
      CanSetText[F, HTMLSpanElement](Text("."))

  def wired[F[_]: Monad]: F[HTMLDivElement] =
    containerDiv >>=
      CanAppendChild[F, HTMLDivElement](thisSiteIsLicensedUnderThe) >>=
      CanAppendChild[F, HTMLDivElement](mitLicense) >>=
      CanAppendChild[F, HTMLDivElement](period) >>=
      CanAppendChild[F, HTMLDivElement](BR) >>=
      CanAppendChild[F, HTMLDivElement](thisSiteIsOpenSource) >>=
      CanAppendChild[F, HTMLDivElement](improveThisSite) >>=
      CanAppendChild[F, HTMLDivElement](period) >>=
      CanAppendChild[F, HTMLDivElement](BR) >>=
      CanAppendChild[F, HTMLDivElement](poweredBy) >>=
      CanAppendChild[F, HTMLDivElement](githubPages) >>=
      CanAppendChild[F, HTMLDivElement](period) >>=
      CanAppendChild[F, HTMLDivElement](BR)

}
