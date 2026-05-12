package io.tlf.jme.jfx.injfx;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationThreadExecutorTest {

    @Test
    void executesQueuedTasksInOrder() {
        var executor = ApplicationThreadExecutor.getInstance();
        var values = new ArrayList<Integer>();

        executor.addToExecute(() -> values.add(1));
        executor.addToExecute(() -> values.add(2));

        executor.execute();

        assertEquals(List.of(1, 2), values);
    }

    @Test
    void clearsTasksAfterExecution() {
        var executor = ApplicationThreadExecutor.getInstance();
        var values = new ArrayList<Integer>();

        executor.addToExecute(() -> values.add(1));
        executor.execute();
        executor.execute();

        assertEquals(List.of(1), values);
    }

    @Test
    void leavesTasksQueuedDuringExecutionForNextPass() {
        var executor = ApplicationThreadExecutor.getInstance();
        var values = new ArrayList<Integer>();

        executor.addToExecute(() -> {
            values.add(1);
            executor.addToExecute(() -> values.add(2));
        });

        executor.execute();
        assertEquals(List.of(1), values);

        executor.execute();
        assertEquals(List.of(1, 2), values);
    }
}
