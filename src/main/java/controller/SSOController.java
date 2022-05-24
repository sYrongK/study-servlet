package main.java.controller;

import main.java.NaverSSOClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "*.sso")
public class SSOController extends HttpServlet {

    private final NaverSSOClient NAVER_SSO_CLIENT = new NaverSSOClient();
    /*
    각각의 api request url 또한 네이버측에서 사전에 정해둔 변하지 않는 값.
    별도 관리가 필요하나, Java Servlet만 사용하는 현 시점에선 별도 관리가 필요한 정보라는 것을 보여주기 위해서 클래스 변수로 선언해두는 정도로 구현했음

    client_id, client_secret : 네이버측에서 클라이언트마다 다르게 발급해주는 인증 정보
    각각의 api request url : 네이버에서 로그인 sso를 이용하려면 여기로 요청을 보내라고 정해둔 주소. 모든 클라이언트에게 동일하다. @WebServlet(name = "주소")와 같은 역할이라고 보면 된다.
    -> NaverSSOClient와는 성격이 다른 정보라서 차이를 뒀음.
     */
    private String AUTHORIZATION_REQUEST_API_URL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";//방식:
    private String TOKEN_REQUEST_API_URL = "https://nid.naver.com/oauth2.0/token?";
    private final String GRANDT_TYPE = "authorization_code";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uri = request.getRequestURI();

        try {
            if (uri.equals("/callback.sso")) {
                /*
                * 네이버 인증 요청을 처리하는 서버에서 보내는 response data
                * code : 네이버 애플리케이션 인증에 성공하면 반환받는 인증 코드. token 발급을 위해 필요함
                * state : 네이버 인증 요청 보낼 떄 생성해서 보냈던 요청 위조 공격 방지용 상태값
                * error : 네이버 애플리케이션 인증 실패하면 반환받는 에러 코드 -> 에러 코드에 따라 다른 처리 할 수 있음
                * error_description : 네이버 로그인 인증에 실패하면 반환받는 에러 메시지
                * */
                String code = request.getParameter("code");
                String state = request.getParameter("state");

                /*
                * 접근 토큰 Access Token 발급 요청  request data
                * grant_type : 인증과정에 대한 구분값 (발급 : authorization_code, 갱신: refresh_token, 삭제: delete)
                * client_id
                * client_secreat
                * code : 로그인 인증 요청 api 호출에 성공하고 리턴받은 인증값
                * state : 네이버 인증 요청 보낼 떄 생성해서 보냈던 요청 위조 공격 방지용 상태값
                * refresh_token
                * access_token
                * service_provider : 토큰 삭제 요청시에 사용하는 인증 제공자 정보 ('NAVER')
                 */

                String callback_url = "http://localhost:8080/token_callback.sso";

                String url = getTokenRequestURL(callback_url, code, state);

                String resp = callApi(url);
            }
            if (uri.equals("token_callback.sso")) {
                System.out.println("dddd");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Servlet Error :: MainServlet");
            response.setStatus(500);
//            response.sendRedirect("/common/Error.jsp");
        }
    }

    private String callApi(String url) throws IOException, ParseException {
        CloseableHttpClient httpClient = HttpClients.createDefault();//http client 생성
        HttpPost httpPost = new HttpPost(url);//Post 설정 및 url 지정
        httpPost.addHeader("Content-type", "application/json");//respons를 json으로 받아야 한다.//MediaType이 스프링 라이브러리네
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

        String json = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
        System.out.println(json);
        System.out.println(httpResponse.getCode());

        InputStream content = httpResponse.getEntity().getContent();

        InputStreamReader inputStreamReader = new InputStreamReader(content);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        String line;
        StringBuffer response = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        System.out.println(response);

        httpClient.close();
        return json;
    }

    private String getTokenRequestURL(String callback_url, String code, String state) {
        TOKEN_REQUEST_API_URL += "grant_type="+GRANDT_TYPE;
        TOKEN_REQUEST_API_URL += "&client_id="+NAVER_SSO_CLIENT.getCliendId();
        TOKEN_REQUEST_API_URL += "&client_secret="+NAVER_SSO_CLIENT.getClientSecret();
        TOKEN_REQUEST_API_URL += "&redirect_url="+callback_url;
        TOKEN_REQUEST_API_URL += "&code="+code;
        TOKEN_REQUEST_API_URL += "&state="+state;

        return TOKEN_REQUEST_API_URL;
    }
}
