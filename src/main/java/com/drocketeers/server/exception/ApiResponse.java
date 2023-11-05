package com.drocketeers.server.exception;

import java.time.ZonedDateTime;

public record ApiResponse(
        String message,
        ZonedDateTime dateTime
) {
}
