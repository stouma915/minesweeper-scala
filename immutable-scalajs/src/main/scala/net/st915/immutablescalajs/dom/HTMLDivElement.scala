package net.st915.immutablescalajs.dom

import net.st915.immutablescalajs.TagName
import net.st915.immutablescalajs.dom.properties.*

final case class HTMLDivElement(
  cssClass: Option[CSSClass] = None,
  id: Option[ID] = None,
  childs: List[HTMLElement] = List()
) extends HTMLElement(TagName.Div)
