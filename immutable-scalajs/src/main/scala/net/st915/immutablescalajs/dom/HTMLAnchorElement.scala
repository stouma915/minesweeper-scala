package net.st915.immutablescalajs.dom

import net.st915.immutablescalajs.TagName
import net.st915.immutablescalajs.properties.*

final case class HTMLAnchorElement(
  cssClass: Option[CSSClass] = None,
  id: Option[ID] = None,
  hyperlink: Option[Hyperlink] = None,
  text: Option[Text] = None
) extends HTMLElement(TagName.Anchor)
