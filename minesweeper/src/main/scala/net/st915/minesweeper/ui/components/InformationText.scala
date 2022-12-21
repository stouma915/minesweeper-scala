package net.st915.minesweeper.ui.components

import cats.Monad
import net.st915.immutablescalajs.Components.BR
import net.st915.immutablescalajs.componentcreators.*
import net.st915.immutablescalajs.dom.properties.*

object InformationText {

  import cats.syntax.flatMap.*

  import net.st915.immutablescalajs.componentcreators.instances.all.given

  import net.st915.immutablescalajs.dom.typealiases.*

  def containerDiv[F[_]: Monad]: F[Div] =
    CanCreateElement[F, Div]() >>=
      CanSetCSSClass[F, Div](CSSClass("informationText"))

  def informationText[F[_]: Monad]: F[H1] =
    CanCreateElement[F, H1]() >>=
      CanSetText[F, H1](Text("Currently Under Development."))

  def wired[F[_]: Monad]: F[Div] =
    containerDiv >>=
      CanAppendChild[F, Div](informationText) >>=
      CanAppendChild[F, Div](BR)

}
