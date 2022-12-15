package net.st915.immutablescalajs

object TagName {

  object Anchor extends TagName("a")
  object BR extends TagName("br")
  object Div extends TagName("div")
  object H1 extends TagName("h1")
  object Paragraph extends TagName("p")
  object Span extends TagName("span")

}

sealed class TagName private (raw: String) {

  def parse: String = raw

}
