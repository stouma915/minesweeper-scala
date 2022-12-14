package net.st915.immutablescalajs.dom

import net.st915.immutablescalajs.TagName
import net.st915.immutablescalajs.dom.properties.*

final case class HTMLParagraphElement(
  cssClass: Option[CSSClass] = None,
  id: Option[ID] = None,
  text: Option[Text] = None
) extends HTMLElement(TagName.Paragraph)
