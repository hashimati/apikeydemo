package io.hashimati.security.token


import io.micronaut.security.token.reader.HttpHeaderTokenReader
import jakarta.inject.Singleton

@Singleton
class ApiKeyTokenReader extends HttpHeaderTokenReader {
    private static final String API_TOKEN_HEADER = "My-Token"

    @Override
    protected String getPrefix() {
        return null
    }

    @Override
    protected String getHeaderName() {
        return API_TOKEN_HEADER
    }
}

