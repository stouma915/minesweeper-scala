package net.st915.immutablescalajs.dom.attributes

import net.st915.immutablescalajs.dom.properties.Text

trait HasText[A] {

  def text: Option[Text]

  def copyWithNewText(newText: Option[Text]): A

}
