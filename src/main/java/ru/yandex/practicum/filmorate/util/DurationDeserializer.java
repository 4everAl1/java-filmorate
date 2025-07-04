package ru.yandex.practicum.filmorate.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Duration;

public class DurationDeserializer extends JsonDeserializer<Duration> {

    @Override
    public Duration deserialize(JsonParser pars, DeserializationContext context) throws IOException {
        return Duration.ofMinutes(pars.getLongValue());
    }
}
