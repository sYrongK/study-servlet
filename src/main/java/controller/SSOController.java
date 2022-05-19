package main.java.controller;

import main.java.dto.Member;

import javax.print.URIException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

@WebServlet(name = "*.sso")
public class SSOController extends HttpServlet {

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

        String client_id = "L_6CaDTEasPFMDKOIrvu";
        String client_secret = "KWhGZtFluZ";
        try {
            if (uri.equals("/login.sso")){

                String callback_url = "http://localhost:8080/callback.sso";

                String api_url = "https://nid.naver.com/oauth2.0/authorize?response_type=code";//방식:

                //특정 Charset(인코딩 체계)을 사용하여 문자열을 application/x-www-form-urlencoded 형식으로 변환
                String encode = URLEncoder.encode(callback_url, StandardCharsets.UTF_8);

                SecureRandom random = new SecureRandom();
                BigInteger state = new BigInteger(130, random);

                /*
                인증 요청 필수 정보
                response_type : 네이버 인증 서버 내부 사용값 (기본값 : "code")
                client_id
                redirect_uri
                state : 사이트 간 요청 위조 공격 방지용. 애플리케이션에서 생성한 상태 토큰값으로 url 인코딩을 적용한 값을 사용
                 */
                api_url += "&client_id=" + client_id;
                api_url += "&redirect_uri=" + callback_url;
                api_url += "&state=" + state;

                URL url = new URL(api_url);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                HttpSession session = request.getSession();
                session.setAttribute("state", state);

                connection.connect();
            }
            if (uri.equals("/callback.sso")) {
                /*
                * 네이버 인증 요청을 처리하는 서버에서 보내는 response data
                * code : 네이버 애플리케이션 인증에 성공하면 반환받는 인증 코드. token 발급을 위해 필요함
                * state : 네이버 인증 요청 보낼 떄 생성해서 보냈던 요청 위조 공격 방지용 상태값
                * error : 네이버 애플리케이션 인증 실패하면 반환받는 에러 코드 -> 에러 코드에 따라 다른 처리 할 수 있음
                * error_description : 
                * */
                String code = request.getParameter("code");
                String state = request.getParameter("state");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Servlet Error :: MainServlet");
            response.setStatus(500);
            response.sendRedirect("/common/Error.jsp");
        }
    }
}
