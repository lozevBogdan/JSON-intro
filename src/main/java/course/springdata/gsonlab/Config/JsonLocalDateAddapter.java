package course.springdata.gsonlab.Config;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import net.bytebuddy.asm.Advice;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JsonLocalDateAddapter implements JsonSerializer<LocalDate> {
    @Override
    public JsonElement serialize
            (LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.format(DateTimeFormatter.ISO_DATE));
    }
}
