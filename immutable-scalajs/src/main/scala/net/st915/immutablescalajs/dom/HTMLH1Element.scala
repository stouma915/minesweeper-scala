package net.st915.immutablescalajs.dom

import net.st915.immutablescalajs.TagName
import net.st915.immutablescalajs.dom.attributes.*
import net.st915.immutablescalajs.dom.properties.*

final case class HTMLH1Element(
  override val cssClass: Option[CSSClass],
  override val id: Option[ID],
  override val text: Option[Text]
) extends HTMLElement(TagName.H1)
    with HasCSSClass[HTMLH1Element]
    with HasID[HTMLH1Element]
    with HasText[HTMLH1Element] {

  override def copyWithNewCSSClass(newCSSClass: Option[CSSClass]): HTMLH1Element =
    copy(cssClass = newCSSClass)

  override def copyWithNewID(newID: Option[ID]): HTMLH1Element =
    copy(id = newID)

  override def copyWithNewText(newText: Option[Text]): HTMLH1Element =
    copy(text = newText)

}
