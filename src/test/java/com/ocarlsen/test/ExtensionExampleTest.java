package com.ocarlsen.test;

import static org.mockito.Mockito.inOrder;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This example shows how to use the library with jUnit extension.
 * If you comment out the {@code @ExtendWith(MockLoggerExtension.class)} then this test fails.
 */
@ExtendWith(MockLoggerExtension.class)
public class ExtensionExampleTest {

    @ParameterizedTest
    @ValueSource(shorts = {1, 2, 3})
    public void testLogging(short sample) {

        // Given
        final MyLoggingClass loggingInstance = new MyLoggingClass();

        // When
        loggingInstance.loggingMethod();

        // Then
        final Logger mockLogger = LoggerFactory.getLogger("MyLoggingClass");

        final InOrder inOrder = inOrder(mockLogger);
        inOrder.verify(mockLogger).debug("this is a debug message");
        inOrder.verify(mockLogger).info("this is an info message");
        inOrder.verify(mockLogger).warn("this is a warn message");
        inOrder.verify(mockLogger).error("this is an error message");
        inOrder.verifyNoMoreInteractions();
    }

    private static class MyLoggingClass {

        private final Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());

        public void loggingMethod() {
            logger.debug("this is a debug message");
            logger.info("this is an info message");
            logger.warn("this is a warn message");
            logger.error("this is an error message");
        }
    }
}