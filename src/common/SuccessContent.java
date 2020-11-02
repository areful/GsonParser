package common;

/**
 * Created by areful on 2020/10/24.
 */
public class SuccessContent<T> {
    private final Response<T> response;

    public SuccessContent(Response<T> response) {
        this.response = response;
    }

    public Response<T> getResBody() {
        return response;
    }
}