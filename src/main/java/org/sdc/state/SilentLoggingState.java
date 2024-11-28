package org.sdc.state;

import org.sdc.logger.ThreadSafeLogger;

/**
 * Silent logging state - suppresses all logging.
 */
public class SilentLoggingState implements LoggerState {
    @Override
    public void log(ThreadSafeLogger logger, String message, LoggingLevel level) {
        // No-op: silently ignore all log messages
    }

    @Override
    public String getStateName() {
        return "SILENT";
    }
}
