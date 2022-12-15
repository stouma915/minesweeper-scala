package net.st915.immutablescalajs.dom

import net.st915.immutablescalajs.TagName
import net.st915.immutablescalajs.dom.attributes.*
import net.st915.immutablescalajs.dom.properties.*

final case class HTMLH1Element(
  override val cssClass: Option[CSSClass],
  override val id: Option[ID],
  override val text: Option[Text]
) extends HTMLElement(TagName.H1)
    with HasCSSClass
    with HasID
    with HasText
