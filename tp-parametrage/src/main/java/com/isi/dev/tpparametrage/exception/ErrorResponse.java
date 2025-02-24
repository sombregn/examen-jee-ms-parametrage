package com.isi.dev.tpparametrage.exception;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
