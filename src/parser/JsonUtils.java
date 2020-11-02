package parser;


import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by areful on 2020/10/24.
 */
public class JsonUtils {
    private static final String TAG = "JsonUtils";
    private static final Gson gson = new GsonBuilder().create();

    public static <T> T fromJson(String json, Type typeOfT) throws JsonSyntaxException {
        try {
            return gson.fromJson(json, typeOfT);
        } catch (JsonSyntaxException e) {
            Log.e(TAG, "fromJson: " + json);
            throw e;
        }
    }

    public static ParameterizedType buildType(final Class<?> raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }

    public static String toJson(Object src) {
        return gson.toJson(src);
    }
}
