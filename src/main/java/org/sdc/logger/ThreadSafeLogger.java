package org.sdc.logger;

import org.sdc.state.LoggerState;
import org.sdc.state.LoggingLevel;
import org.sdc.state.NormalLoggingState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Thread-safe Singleton Logger with State pattern support.
 */
public class ThreadSafeLogger {
    // Private static final instance of Log4J2 Logger
    private static final Logger LOGGER = LogManager.getLogger(ThreadSafeLogger.class);

    // Current logging state
    private LoggerState currentState;

    // Private constructor to prevent direct instantiation
    private ThreadSafeLogger() {
        // Default to normal logging state
        this.currentState = new NormalLoggingState();
    }

    // Static inner class for lazy initialization
    private static class LoggerHolder {
        private static final ThreadSafeLogger INSTANCE = new ThreadSafeLogger();
    }

    /**
     * Returns the singleton instance of the Logger.
     *
     * @return Singleton instance of ThreadSafeLogger
     */
    public static ThreadSafeLogger getInstance() {
        return LoggerHolder.INSTANCE;
    }

    /**
     * Change the current logging state.
     *
     * @param newState The new logging state
     */
    public synchronized void setState(LoggerState newState) {
        this.currentState = newState;
    }

    /**
     * Get the current state name.
     *
     * @return Name of the current logging state
     */
    public String getCurrentStateName() {
        return currentState.getStateName();
    }

    /**
     * Log a message with the current state.
     *
     * @param message The message to log
     * @param level The logging level
     */
    public synchronized void log(String message, LoggingLevel level) {
        currentState.log(this, message, level);
    }

    /**
     * Get the raw Log4J2 logger for internal use.
     *
     * @return The underlying Log4J2 Logger
     */
    public Logger getRawLogger() {
        return LOGGER;
    }

    // Convenience methods for different log levels
    public void logInfo(String message) {
        log(message, org.sdc.state.LoggingLevel.INFO);
    }

    public void logDebug(String message) {
        log(message, org.sdc.state.LoggingLevel.DEBUG);
    }

    public void logError(String message) {
        log(message, org.sdc.state.LoggingLevel.ERROR);
    }

    public void logError(String message, Throwable throwable) {
        log(message + ": " + throwable.getMessage(), org.sdc.state.LoggingLevel.ERROR);
    }
}