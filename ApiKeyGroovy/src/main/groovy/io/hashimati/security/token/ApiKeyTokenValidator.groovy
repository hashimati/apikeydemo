package io.hashimati.security.token

import io.hashimati.security.repository.ApiKeyRepository
import io.micronaut.core.async.publisher.Publishers
import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.token.validator.TokenValidator
import jakarta.inject.Singleton
import org.reactivestreams.Publisher




@Singleton
class ApiKeyTokenValidator implements TokenValidator {

    private final ApiKeyRepository apiKeyRepository


    ApiKeyTokenValidator(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository
    }

    @Override
    Publisher<Authentication> validateToken(String token, HttpRequest<?> request) {
        if (request == null || !request.getPath().startsWith("/api")) {
            return Publishers.empty()
        }
        return apiKeyRepository.findBySecret(token)
                .filter(x-> x.getExpiry().isAfter(java.time.Instant.now()))
                .map(principle ->Authentication.build(principle.getName()))
                .map(Publishers::just).orElse(Publishers.empty())

    }
}

