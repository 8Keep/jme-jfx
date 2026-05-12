package io.tlf.jme.jfx;

import com.jme3.input.event.KeyInputEvent;
import com.jme3.input.event.MouseButtonEvent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JmeMemoryInputHandlerTest {

    @Test
    void tracksPressedAndReleasedMouseButtons() {
        var handler = new JmeMemoryInputHandler();

        var pressed = new MouseButtonEvent(1, true, 10, 20);
        handler.onMouseButtonEvent(pressed);

        assertSame(pressed, handler.getJmeMouseButtonEvents().get(1));

        handler.onMouseButtonEvent(new MouseButtonEvent(1, false, 10, 20));

        assertFalse(handler.getJmeMouseButtonEvents().containsKey(1));
    }

    @Test
    void tracksPressedAndReleasedKeys() {
        var handler = new JmeMemoryInputHandler();

        var pressed = new KeyInputEvent(32, ' ', true, false);
        handler.onKeyEvent(pressed);

        assertSame(pressed, handler.getJmeKeyInputEvents().get(32));

        handler.onKeyEvent(new KeyInputEvent(32, ' ', false, false));

        assertFalse(handler.getJmeKeyInputEvents().containsKey(32));
    }

    @Test
    void ignoresUnknownKeyCode() {
        var handler = new JmeMemoryInputHandler();

        handler.onKeyEvent(new KeyInputEvent(0, '\0', true, false));

        assertTrue(handler.getJmeKeyInputEvents().isEmpty());
    }

    @Test
    void repeatingKeyReleaseDoesNotClearPressedKey() {
        var handler = new JmeMemoryInputHandler();
        var pressed = new KeyInputEvent(32, ' ', true, false);
        handler.onKeyEvent(pressed);

        handler.onKeyEvent(new KeyInputEvent(32, ' ', false, true));

        assertSame(pressed, handler.getJmeKeyInputEvents().get(32));
    }
}
