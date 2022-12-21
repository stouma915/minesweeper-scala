package net.st915.minesweeper.ui.components

import cats.Monad
import net.st915.immutablescalajs.componentcreators.*
import net.st915.immutablescalajs.dom.*
import net.st915.immutablescalajs.dom.properties.*

object InformationText {

  import cats.syntax.flatMap.*

  import net.st915.immutablescalajs.componentcreators.instances.all.given

  def containerDiv[F[_]: Monad]: F[HTMLDivElement] =
    CanCreateElement[F, HTMLDivElement]() >>=
      CanSetCSSClass[F, HTMLDivElement](CSSClass("informationText"))

  def informationText[F[_]: Monad]: F[HTMLH1Element] =
    CanCreateElement[F, HTMLH1Element]() >>=
      CanSetText[F, HTMLH1Element](Text("Currently Under Development."))

  def br[F[_]: Monad]: F[HTMLBRElement] =
    CanCreateElement[F, HTMLBRElement]()

  def wired[F[_]: Monad]: F[HTMLDivElement] =
    containerDiv >>= 
      CanAppendChild[F, HTMLDivElement](informationText) >>=
      CanAppendChild[F, HTMLDivElement](br)

}
