package ch.zhaw.soe.swen1.le10.chat.domain;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


/**
 * Combined tests for the online chat domain logic. Because domain classes are
 * quite simple, only integration tests have to be written.
 *
 */
@ExtendWith(MockitoExtension.class)
public class OnlineChatTest {
    @BeforeEach
    public void setUp() throws Exception {
        //TODO
    }

    @Test
    public void myTest() {
        //TODO
        assertTrue(true);
    }
}