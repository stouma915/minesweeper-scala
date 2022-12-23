package net.st915.immutablescalajs.dom

import net.st915.immutablescalajs.TagName
import net.st915.immutablescalajs.dom.attributes.*
import net.st915.immutablescalajs.dom.properties.*

final case class HTMLAnchorElement(
  override val cssClass: Option[CSSClass],
  override val id: Option[ID],
  override val hyperlink: Option[Hyperlink],
  override val text: Option[Text],
  override val childElements: List[HTMLElement]
) extends HTMLElement(TagName.Anchor)
    with HasCSSClass[HTMLAnchorElement]
    with HasID[HTMLAnchorElement]
    with HasHyperlink[HTMLAnchorElement]
    with HasText[HTMLAnchorElement]
    with HasChildElements[HTMLAnchorElement] {

  override def copyWithNewCSSClass(newCSSClass: Option[CSSClass]): HTMLAnchorElement =
    copy(cssClass = newCSSClass)

  override def copyWithNewID(newID: Option[ID]): HTMLAnchorElement =
    copy(id = newID)

  override def copyWithNewHyperlink(newHyperlink: Option[Hyperlink]): HTMLAnchorElement =
    copy(hyperlink = newHyperlink)

  override def copyWithNewText(newText: Option[Text]): HTMLAnchorElement =
    copy(text = newText)

  override def copyWithNewChildElements(newChildElements: List[HTMLElement]): HTMLAnchorElement =
    copy(childElements = newChildElements)

}
