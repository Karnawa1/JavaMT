package org.sdc.threads;

import org.sdc.logger.ThreadSafeLogger;
import org.sdc.utils.ConfigReader;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * A thread implementation that demonstrates logging in a concurrent environment.
 */
public class LoggerThread implements Callable<Void> {
    private final String threadName;
    private final int iterations;
    private final ThreadSafeLogger logger;

    /**
     * Constructor for LoggerThread.
     *
     * @param threadName The name of the thread
     * @param iterations Number of log iterations
     */
    public LoggerThread(String threadName, int iterations) {
        this.threadName = threadName;
        this.iterations = iterations;
        this.logger = ThreadSafeLogger.getInstance();
    }

    /**
     * Main thread execution method.
     *
     * @return null (Callable interface requirement)
     * @throws Exception If thread execution fails
     */
    @Override
    public Void call() throws Exception {
        for (int i = 0; i < iterations; i++) {
            // Log messages at different levels
            logger.logInfo(threadName + ": Iteration " + i + " - Info message");
            logger.logDebug(threadName + ": Iteration " + i + " - Debug message");

            // Simulate some work with a time delay
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                logger.logError(threadName + ": Thread interrupted", e);
                Thread.currentThread().interrupt();
                break;
            }
        }
        return null;
    }
}
