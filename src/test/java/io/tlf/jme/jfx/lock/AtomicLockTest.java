package io.tlf.jme.jfx.lock;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AtomicLockTest {

    @Test
    void tryLockReflectsLockState() {
        var lock = new AtomicLock();

        assertTrue(lock.tryLock());
        assertFalse(lock.tryLock());

        lock.unlock();

        assertTrue(lock.tryLock());
        lock.unlock();
    }
}
