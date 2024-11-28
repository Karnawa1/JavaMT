package org.sdc.state;

import org.sdc.logger.ThreadSafeLogger;

/**
 * Normal logging state - logs all messages.
 */
public class NormalLoggingState implements LoggerState {
    @Override
    public void log(ThreadSafeLogger logger, String message, LoggingLevel level) {
        switch (level) {
            case INFO:
                logger.getRawLogger().info(message);
                break;
            case DEBUG:
                logger.getRawLogger().debug(message);
                break;
            case ERROR:
                logger.getRawLogger().error(message);
                break;
            case WARN:
                logger.getRawLogger().warn(message);
                break;
        }
    }

    @Override
    public String getStateName() {
        return "NORMAL";
    }
}
