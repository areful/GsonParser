package parser;

import common.ErrorContent;
import common.Header;
import common.Response;
import common.SuccessContent;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by areful on 2020/10/24.
 */
public class ResponseParser<T> {
    protected void onSuccess(SuccessContent<T> content) {
    }

    protected void onError(ErrorContent content) {
    }

    public void parse(String json) {
        try {
            Type objectType = JsonUtils.buildType(Response.class, getResClass());
            Response<T> resObj = JsonUtils.fromJson(json, objectType);
            Header header = resObj.getHeader();
            if (header == null || header.getRspCode() != 0) {
                ErrorContent error = new ErrorContent(1, "error result");
                onError(error);
                return;
            }

            onSuccess(new SuccessContent<>(resObj));
            return;
        } catch (Exception ignored) {
        }

        ErrorContent error = new ErrorContent(1997, "parse exception");
        onError(error);
    }

    private Class<T> getResClass() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        //noinspection unchecked
        return (Class<T>) type.getActualTypeArguments()[0];
    }
}