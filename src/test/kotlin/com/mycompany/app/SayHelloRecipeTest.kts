package org.openrewrite.samples

import org.junit.jupiter.api.Test
import org.openrewrite.java.JavaParser
import org.openrewrite.java.JavaRecipeTest

class SayHelloRecipeTest: RewriteTest {

    override fun defaults(spec: RecipeSpec) {
        spec
            .recipe(SayHelloRecipe("com.yourorg.A"))
    }

    @Test
    fun addsHelloToA() = rewriteRun(
        java(
            """
                package com.yourorg;
    
                class A {
                }
            """,
            """
                package com.yourorg;
    
                class A {
                    public String hello() {
                        return "Hello from com.yourorg.A!";
                    }
                }
            """
        )
    )

    @Test
    fun doesNotChangeExistingHello() = rewriteRun(
        java(
            """
                package com.yourorg;
    
                class A {
                    public String hello() { return ""; }
                }
            """
        )
    )

    @Test
    fun doesNotChangeOtherClass() = rewriteRun(
        java(
            """
                package com.yourorg;
    
                class B {
                }
            """
        )
    )
}