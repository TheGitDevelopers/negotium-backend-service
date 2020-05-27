package com.negotium.negotiumapp.security.config.date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomDateSerializer extends JsonSerializer<LocalDateTime> {
    private DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern(NegotiumDateTimeConfig.dateFormat);

    @Override
    public void serialize(LocalDateTime date, JsonGenerator gen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        final String dateString = date.format(this.formatter);
        gen.writeString(dateString);
    }
}