package main.java;

/**
 * 네이버 로그인 api를 이용하기 위해 발급받은 client_id, client_secret은 네이버 로그인 api를 사용하겠다고 사전 등록해둔 클라이언트임을 증명하는 정보.
 * 변해선 안되는 값.
 *
 * dto로 뺀 이유 : 별도로 관리가 필요한 정보라는 것을 보여주기 위해 일부러 dto로 뺐음.
 * setter 구현하지 말 것.
 */
public class NaverSSOClient {

    private final String cliendId;
    private final String clientSecret;

    public NaverSSOClient() {
        this.cliendId = "L_6CaDTEasPFMDKOIrvu";
        this.clientSecret = "KWhGZtFluZ";
    }

    public String getCliendId() {
        return cliendId;
    }

    public String getClientSecret() {
        return clientSecret;
    }
}
