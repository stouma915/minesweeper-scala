package net.st915.immutablescalajs.dom

import net.st915.immutablescalajs.TagName
import net.st915.immutablescalajs.dom.attributes.*
import net.st915.immutablescalajs.dom.properties.*

final case class HTMLSpanElement(
  override val cssClass: Option[CSSClass],
  override val id: Option[ID],
  override val text: Option[Text]
) extends HTMLElement(TagName.Span)
    with HasCSSClass[HTMLSpanElement]
    with HasID[HTMLSpanElement]
    with HasText[HTMLSpanElement] {

  override def copyWithNewCSSClass(newCSSClass: Option[CSSClass]): HTMLSpanElement =
    copy(cssClass = newCSSClass)

  override def copyWithNewID(newID: Option[ID]): HTMLSpanElement =
    copy(id = newID)

  override def copyWithNewText(newText: Option[Text]): HTMLSpanElement =
    copy(text = newText)

}
