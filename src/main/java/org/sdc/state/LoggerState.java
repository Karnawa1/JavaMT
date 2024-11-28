package org.sdc.state;

import org.sdc.logger.ThreadSafeLogger;

/**
 * State interface for Logger states.
 */
public interface LoggerState {
    /**
     * Handle logging operation for the current state.
     *
     * @param logger The logger context
     * @param message The message to log
     * @param level The logging level
     */
    void log(ThreadSafeLogger logger, String message, LoggingLevel level);

    /**
     * Get the current state of the logger.
     *
     * @return Current state description
     */
    String getStateName();
}

