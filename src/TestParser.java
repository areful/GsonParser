import com.google.gson.Gson;
import common.ErrorContent;
import common.Header;
import common.Response;
import common.SuccessContent;
import models.ResBody1;
import models.ResBody2;
import parser.ResponseParser;

/**
 * Created by areful on 2020/10/24.
 */
public class TestParser {

    public static void main(String[] args) {
        testParseResBody1();

        testParseResBody2();

        testParseInvalidJson();
    }

    private static void testParseResBody1() {
        {
            //make json string
            Response<ResBody1> rsp = new Response<>();
            ResBody1 body = new ResBody1();
            body.setCode(1997);
            rsp.setBody(body);
            Header header = new Header();
            header.setRspDesc("areful");
            rsp.setHeader(header);
            String json = new Gson().toJson(rsp);
            System.out.println(json);
        }

        String json = "{\"header\":{\"rspCode\":0,\"rspDesc\":\"areful\"},\"body\":{\"code\":1997}}";
        ResponseParser<ResBody1> parser = new ResponseParser<ResBody1>() {
            @Override
            protected void onSuccess(SuccessContent<ResBody1> content) {
                super.onSuccess(content);
                Header header = content.getResBody().getHeader();
                ResBody1 resBody1 = content.getResBody().getBody();
                System.out.println(header.getRspDesc());
                System.out.println(resBody1.getCode());
            }
        };
        parser.parse(json);
    }

    private static void testParseResBody2() {
        {
            //make json string
            Response<ResBody2> rsp = new Response<>();
            ResBody2 body = new ResBody2();
            body.setName("areful");
            rsp.setBody(body);
            Header header = new Header();
            header.setRspCode(1997);
            rsp.setHeader(header);
            String json = new Gson().toJson(rsp);
            System.out.println(json);
        }

        String json = "{\"header\":{\"rspCode\":1997},\"body\":{\"name\":\"areful\"}}";
        ResponseParser<ResBody2> parser = new ResponseParser<ResBody2>() {
            @Override
            protected void onSuccess(SuccessContent<ResBody2> content) {
                super.onSuccess(content);
                Header header = content.getResBody().getHeader();
                ResBody2 resBody = content.getResBody().getBody();
                System.out.println(resBody.getName());
                System.out.println(header.getRspCode());
            }
        };
        parser.parse(json);
    }

    private static void testParseInvalidJson() {
        String json = "not a json text";
        ResponseParser<ResBody2> parser = new ResponseParser<ResBody2>() {
            @Override
            protected void onError(ErrorContent content) {
                super.onError(content);
                System.out.println(content.getHeader().getRspDesc());
            }
        };
        parser.parse(json);
    }
}