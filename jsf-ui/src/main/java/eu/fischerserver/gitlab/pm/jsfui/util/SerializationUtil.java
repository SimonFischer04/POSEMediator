package eu.fischerserver.gitlab.pm.jsfui.util;

import eu.fischerserver.gitlab.pm.jsfui.model.PMData;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

public class SerializationUtil {
    private static <T> T parse(String dataString, Class<T> clazz) {
        Jsonb jsonb = JsonbBuilder.create();
        return jsonb.fromJson(dataString, clazz);
    }

    public static PMData parsePMData(String dataString) {
        return parse(dataString, PMData.class);
    }

    private static <T> T parse(byte[] payload, @SuppressWarnings("SameParameterValue") Class<T> clazz) {
        return parse(new String(payload), clazz);
    }

    public static PMData parsePMData(byte[] payload) {
        return parse(payload, PMData.class);
    }

    private static String stringify(Object data) {
        Jsonb jsonb = JsonbBuilder.create();
        return jsonb.toJson(data);
    }

    public static byte[] toPayload(Object data) {
        return stringify(data).getBytes();
    }
}
