package net.dillon.speedrunnermod.util;

import net.minecraft.server.MinecraftServer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

/**
 * An alternative to {@link TimerTask}, which allows execution of code after a set amount of seconds.
 */
public class TaskScheduler {
    private static final List<ScheduledTask> TASKS = new ArrayList<>();

    /**
     * Schedule a task (executes after {@code delayTicks}).
     */
    public static void schedule(int delayTicks, Runnable task) {
        TASKS.add(new ScheduledTask(delayTicks, task));
    }

    /**
     * Tick the task delay, and then execute.
     */
    public static void tick(MinecraftServer server) {
        Iterator<ScheduledTask> iterator = TASKS.iterator();

        while (iterator.hasNext()) {
            ScheduledTask task = iterator.next();
            task.ticks--;

            if (task.ticks <= 0) {
                task.task.run();
                iterator.remove();
            }
        }
    }

    /**
     * Create a new task.
     */
    private static class ScheduledTask {
        int ticks;
        Runnable task;

        ScheduledTask(int ticks, Runnable task) {
            this.ticks = ticks;
            this.task = task;
        }
    }
}