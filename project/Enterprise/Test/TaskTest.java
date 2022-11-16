package Enterprise.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import Enterprise.*;

@TestInstance(Lifecycle.PER_CLASS)
class TaskTest {

    Task task;

    @BeforeAll
    void testTask() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Task("tarea 1", 20, TaskType.FUNCTIONAL));
        assertEquals("[ERROR] Task's parameter is incorrect!!", ex.getMessage());

        ex = assertThrows(IllegalArgumentException.class, () -> new Task("T:tarea 1", 20, TaskType.FUNCTIONAL));
        assertEquals("[ERROR] Task's parameter is incorrect!!", ex.getMessage());

        ex = assertThrows(IllegalArgumentException.class, () -> new Task("T2 tarea 1", 20, TaskType.FUNCTIONAL));
        assertEquals("[ERROR] Task's parameter is incorrect!!", ex.getMessage());

        ex = assertThrows(IllegalArgumentException.class, () -> new Task("A2:tarea 1", 20, TaskType.FUNCTIONAL));
        assertEquals("[ERROR] Task's parameter is incorrect!!", ex.getMessage());

        ex = assertThrows(IllegalArgumentException.class, () -> new Task("TA2:tarea 1", 20, TaskType.FUNCTIONAL));
        assertEquals("[ERROR] Task's parameter is incorrect!!", ex.getMessage());

        ex = assertThrows(IllegalArgumentException.class, () -> new Task("2:tarea 1", 20, TaskType.FUNCTIONAL));
        assertEquals("[ERROR] Task's parameter is incorrect!!", ex.getMessage());

        ex = assertThrows(IllegalArgumentException.class, () -> new Task("T2 tarea 1", 20, TaskType.FUNCTIONAL));
        assertEquals("[ERROR] Task's parameter is incorrect!!", ex.getMessage());

        task = new Task("T2:", 20, TaskType.FUNCTIONAL);

        task = new Task("T202:tarea", 70, TaskType.FUNCTIONAL);

        ex = assertThrows(IllegalArgumentException.class, () -> new Task("T122: tarea 1", -20, TaskType.FUNCTIONAL));
        assertEquals("[ERROR] Task's parameter is incorrect!!", ex.getMessage());

        ex = assertThrows(IllegalArgumentException.class, () -> new Task("T122: tarea 1", 101, TaskType.FUNCTIONAL));
        assertEquals("[ERROR] Task's parameter is incorrect!!", ex.getMessage());

        task = new Task("T01: tarea 1", 40, TaskType.FUNCTIONAL);
    }

    @Test
    void testGetName() {
        assertEquals("T01: tarea 1", task.getName());
    }

    @Test
    void testGetType() {
        assertEquals(TaskType.FUNCTIONAL, task.getType());
    }

    @Test
    void testToString() {
        assertEquals("(FUNCTIONAL) T01: tarea 1 - workload 40%", task.toString());
    }

}
