package net.st915.immutablescalajs

object TagName {

  final val Anchor = TagName("a")
  final val BR = TagName("br")
  final val Div = TagName("div")
  final val H1 = TagName("h1")
  final val Paragraph = TagName("p")
  final val Span = TagName("span")

}

final class TagName private (raw: String) {

  def unwrap: String = raw

}
