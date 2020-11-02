package common;

/**
 * Created by areful on 2020/10/24.
 */
public class ErrorContent {
    private Header header;

    public ErrorContent(int code, String desc) {
        header = new Header();
        header.setRspCode(code);
        header.setRspDesc(desc);
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }
}
