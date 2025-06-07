package com.hmm.test.infraestructure.config;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    // Define el formato que quieres usar para serializar/deserializar la fecha
    // Puedes usar ISO_LOCAL_DATE para "yyyy-MM-dd" o un formato personalizado
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public JsonElement serialize(LocalDate localDate, Type srcType, JsonSerializationContext context) {
        // Convierte LocalDate a un String usando el formato definido
        return new JsonPrimitive(formatter.format(localDate));
    }

    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        // Convierte el String JSON de vuelta a LocalDate
        // Maneja el caso de que la fecha sea nula en el JSON
        if (json == null || json.getAsString().isEmpty()) {
            return null;
        }
        return LocalDate.parse(json.getAsString(), formatter);
    }
}