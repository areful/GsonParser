package common;

import parser.JsonUtils;

/**
 * Created by areful on 2020/10/24.
 */
public class Response<T> {
    private Header header;
    private T body;

    public Header getHeader() {
        return header;
    }

    public T getBody() {
        return body;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}