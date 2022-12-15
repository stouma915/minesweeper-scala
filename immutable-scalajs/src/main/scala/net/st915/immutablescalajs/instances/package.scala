package net.st915.immutablescalajs

package object instances {

  object canAppendChildInstances extends CanAppendChildInstances
  object canCreateElementInstances extends CanCreateElementInstances
  object canUpdateCSSClassInstances extends CanUpdateCSSClassInstances
  object canUpdateHyperlinkInstances extends CanUpdateHyperlinkInstances
  object canUpdateIDInstances extends CanUpdateIDInstances
  object canUpdateTextInstances extends CanUpdateTextInstances

  object all
      extends CanAppendChildInstances
      with CanCreateElementInstances
      with CanUpdateCSSClassInstances
      with CanUpdateHyperlinkInstances
      with CanUpdateIDInstances
      with CanUpdateTextInstances

}
