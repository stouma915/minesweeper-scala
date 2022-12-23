package net.st915.minesweeper.ui.components

import cats.Monad
import net.st915.immutablescalajs.Components
import net.st915.immutablescalajs.componentcreators.*

object InformationText {

  import cats.syntax.flatMap.*
  import net.st915.immutablescalajs.dom.syntax.propertySyntax.*

  import net.st915.immutablescalajs.componentcreators.instances.all.given

  import net.st915.immutablescalajs.dom.typealiases.*

  def containerDiv[F[_]: Monad]: F[Div] =
    CanCreateElement[F, Div]() >>=
      CanSetCSSClass[F, Div]("informationText".asCSSClass)

  def informationText[F[_]: Monad]: F[H1] =
    CanCreateElement[F, H1]() >>=
      CanSetText[F, H1]("Currently Under Development.".asText)

  def wired[F[_]: Monad]: F[Div] =
    containerDiv >>=
      CanAppendChild[F, Div](informationText) >>=
      CanAppendChild[F, Div](Components.BR)

}
