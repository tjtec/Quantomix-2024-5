package hwr.oop.quantomix.main

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.extensions.system.captureStandardOut
import org.assertj.core.api.Assertions

class MainTest : AnnotationSpec() {

  //@Test
  fun `main prints hello world to stdout`() {
    val output = captureStandardOut {
        //main()
    }.trim()
    Assertions.assertThat(output).isEqualTo("Test")
  }

}