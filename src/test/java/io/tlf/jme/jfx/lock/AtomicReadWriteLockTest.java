package io.tlf.jme.jfx.lock;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AtomicReadWriteLockTest {

    @Test
    void readLockCanBeAcquiredAndReleased() {
        var lock = new AtomicReadWriteLock();

        assertDoesNotThrow(() -> {
            lock.asyncLock();
            lock.asyncUnlock();
        });
    }

    @Test
    void writeLockCanBeAcquiredAndReleased() {
        var lock = new AtomicReadWriteLock();

        assertDoesNotThrow(() -> {
            lock.syncLock();
            lock.syncUnlock();
        });
    }

    @Test
    void standardTryLockIsUnsupported() {
        var lock = new AtomicReadWriteLock();

        assertThrows(UnsupportedOperationException.class, lock::tryLock);
    }
}
