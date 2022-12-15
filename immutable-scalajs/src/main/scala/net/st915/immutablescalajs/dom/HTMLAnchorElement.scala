package net.st915.immutablescalajs.dom

import net.st915.immutablescalajs.TagName
import net.st915.immutablescalajs.dom.attributes.*
import net.st915.immutablescalajs.dom.properties.*

final case class HTMLAnchorElement(
  override val cssClass: Option[CSSClass],
  override val id: Option[ID],
  override val hyperlink: Option[Hyperlink],
  override val text: Option[Text]
) extends HTMLElement(TagName.Anchor)
    with HasCSSClass
    with HasHyperlink
    with HasID
    with HasText
