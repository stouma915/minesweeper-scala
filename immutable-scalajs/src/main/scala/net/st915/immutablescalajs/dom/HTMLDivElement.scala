package net.st915.immutablescalajs.dom

import net.st915.immutablescalajs.TagName
import net.st915.immutablescalajs.dom.attributes.*
import net.st915.immutablescalajs.dom.properties.*

final case class HTMLDivElement(
  override val cssClass: Option[CSSClass],
  override val id: Option[ID]
) extends HTMLElement(TagName.Div)
    with HasCSSClass[HTMLDivElement]
    with HasID[HTMLDivElement] {

  override def copyWithNewCSSClass(newCSSClass: Option[CSSClass]): HTMLDivElement =
    copy(cssClass = newCSSClass)

  override def copyWithNewID(newID: Option[ID]): HTMLDivElement =
    copy(id = newID)

}
