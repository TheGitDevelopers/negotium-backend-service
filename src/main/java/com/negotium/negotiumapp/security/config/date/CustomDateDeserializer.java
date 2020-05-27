package com.negotium.negotiumapp.security.config.date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomDateDeserializer extends JsonDeserializer<LocalDateTime> {
    private DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext context)
            throws IOException, JsonProcessingException {
        ObjectCodec objectCodec = jsonParser.getCodec();
        TextNode node = objectCodec.readTree(jsonParser);
        String date = node.textValue();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(NegotiumDateTimeConfig.dateFormat);
        return LocalDateTime.parse(date, formatter);
    }

}