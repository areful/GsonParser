import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by areful on 2020/10/24.
 */
public class TestParseGenerateType {

    public static void main(String[] args) {
        testParseResBody1();
        testParseResBody2();
    }

    private static void testParseResBody1() {
        String json = "{\"body\":{\"name\":\"areful\"}}";
        ResponseType<ResBody1> r = new Parser<ResBody1>() {
        }.parse(json);

        assert r != null;
        ResBody1 resBody1 = r.getBody();
        System.out.println(resBody1.name);
    }

    private static void testParseResBody2() {
        String json = "{\"body\":{\"code\":1997}}";
        ResponseType<ResBody2> r = new Parser<ResBody2>() {
        }.parse(json);

        assert r != null;
        ResBody2 resBody2 = r.getBody();
        System.out.println(resBody2.code);
    }

    public static class ResBody1 {
        public String name;
    }

    public static class ResBody2 {
        public int code;
    }

    public static class ResponseType<T> {
        private T body;

        public T getBody() {
            return body;
        }
    }

    private static class Parser<T> {
        private static final Gson gson = new GsonBuilder().create();
        private final Class<?> clazz = ResponseType.class;

        public ResponseType<T> parse(String json) {
            try {
                ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
                Type objectType = buildType(clazz, type.getActualTypeArguments());
                return gson.fromJson(json, objectType);
            } catch (Exception ignored) {
            }
            return null;
        }

        private static ParameterizedType buildType(final Class<?> raw, final Type... args) {
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
    }
}