package net.st915.immutablescalajs.dom

import net.st915.immutablescalajs.TagName
import net.st915.immutablescalajs.dom.attributes.*
import net.st915.immutablescalajs.dom.properties.*

final case class HTMLParagraphElement(
  override val cssClass: Option[CSSClass],
  override val id: Option[ID],
  override val text: Option[Text]
) extends HTMLElement(TagName.Paragraph)
    with HasCSSClass[HTMLParagraphElement]
    with HasID[HTMLParagraphElement]
    with HasText[HTMLParagraphElement] {

  override def copyWithNewCSSClass(newCSSClass: Option[CSSClass]): HTMLParagraphElement =
    copy(cssClass = newCSSClass)

  override def copyWithNewID(newID: Option[ID]): HTMLParagraphElement =
    copy(id = newID)

  override def copyWithNewText(newText: Option[Text]): HTMLParagraphElement =
    copy(text = newText)

}
