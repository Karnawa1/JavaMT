package org.sdc.state;

import org.sdc.logger.ThreadSafeLogger;

/**
 * Verbose logging state - logs with additional context.
 */
public class VerboseLoggingState implements LoggerState {
    @Override
    public void log(ThreadSafeLogger logger, String message, LoggingLevel level) {
        String contextMessage = String.format("[VERBOSE] %s - %s",
                Thread.currentThread().getName(), message);

        switch (level) {
            case INFO:
                logger.getRawLogger().info(contextMessage);
                break;
            case DEBUG:
                logger.getRawLogger().debug(contextMessage);
                break;
            case ERROR:
                logger.getRawLogger().error(contextMessage);
                break;
            case WARN:
                logger.getRawLogger().warn(contextMessage);
                break;
        }
    }

    @Override
    public String getStateName() {
        return "VERBOSE";
    }
}
