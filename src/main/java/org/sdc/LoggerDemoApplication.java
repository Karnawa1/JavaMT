package org.sdc;

import org.sdc.logger.ThreadSafeLogger;
import org.sdc.state.SilentLoggingState;
import org.sdc.state.VerboseLoggingState;
import org.sdc.threads.LoggerThread;
import org.sdc.utils.ConfigReader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Main application to demonstrate the thread-safe logger with configuration.
 */
public class LoggerDemoApplication {
    public static void main(String[] args) {
        // Read configuration
        ConfigReader config = new ConfigReader();

        // Read thread and logging configuration from properties file
        int numThreads = Integer.parseInt(config.getProperty("thread.count", "5"));
        int iterations = Integer.parseInt(config.getProperty("thread.iterations", "10"));

        // Get the singleton logger instance
        ThreadSafeLogger logger = ThreadSafeLogger.getInstance();

        // Demonstrate state changes
        logger.logInfo("Starting in NORMAL logging state");

        // Create a thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        // List to store thread futures
        List<Future<Void>> futures = new ArrayList<>();

        // Create and submit threads
        for (int i = 0; i < numThreads; i++) {
            LoggerThread loggerThread = new LoggerThread("Thread-" + (i + 1), iterations);
            futures.add(executorService.submit(loggerThread));
        }

        // Change logging state midway
        logger.setState(new VerboseLoggingState());
        logger.logInfo("Switched to VERBOSE logging state");

        // Wait for all threads to complete
        try {
            for (Future<Void> future : futures) {
                future.get(); // Wait for each thread to complete
            }
        } catch (Exception e) {
            logger.logError("Error in thread execution", e);
        } finally {
            // Change to silent state
            logger.setState(new SilentLoggingState());
            logger.logInfo("Switched to SILENT logging state - this won't be logged");

            // Shutdown the executor service
            executorService.shutdown();
        }

        // Log final message
        logger.logInfo("All threads completed logging");
    }
}