package net.st915.immutablescalajs.dom

import net.st915.immutablescalajs.TagName
import net.st915.immutablescalajs.dom.attributes.*
import net.st915.immutablescalajs.dom.properties.*

final case class HTMLDivElement(
  override val cssClass: Option[CSSClass],
  override val id: Option[ID],
) extends HTMLElement(TagName.Div)
    with HasCSSClass
    with HasID
