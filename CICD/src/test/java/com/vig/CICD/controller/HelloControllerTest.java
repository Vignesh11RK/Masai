package com.vig.CICD.controller;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class HelloControllerTest {

    @Test
    void testHello() {
        HelloController controller = new HelloController();
        assertThat(controller.hello()).isEqualTo("Hello, CI/CD World!");
    }
}
