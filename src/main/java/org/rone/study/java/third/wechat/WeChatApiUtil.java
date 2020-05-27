package org.rone.study.java.third.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Rone    2020/1/8
 */
public class WeChatApiUtil {

    public static final Logger LOGGER = LoggerFactory.getLogger(WeChatApiUtil.class);

//    public static final String APP_ID = "wxb3737b4924fed43d";
//
//    public static final String SECRET = "4e90651965b4a0d292a253fd182f46e2";

    // 稠银凡荣微店
    public static final String APP_ID = "wx2811a6fe33c7d1b4";
    public static final String SECRET = "8b8f1881b00bd17ffd9ae69d295de0ab";

    public static final String LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";

    public static String ACCESS_TOKEN = "";

    public static void main(String[] args) throws Exception {
        String encryptedData = "pvR2DGX75x51ci8f2NW5W439VngJFSkOsprhPHUgLiXw33jIfqguEppoiy62mUaQmcdOZcXcDOkCerBtBXDCQ0VAAzWo8z1najqgWSGgC3HPMCtm3PQB7XI3ZGmk1aDfDjd+dyGfg85IwPuJ4CVrMRnRPM4eyJkkTZsw7EZEOXOThv9j4r+W5HXAm8qI6Opw9KDmLj00z+a1lpEl6q1Nf2ZoV8tHXPN5n+3ygHb5VgYGwtQvATWG5FtEoqT89q018ISJgFqHyYtCIpfZUC/9RwCgoIoSvihawPv+iq4yn1hPX+euMVvPYUCplpMwkDRaGgV50AdfyXiOnTqkuCslj3UFqodDU4t8yvpH0+P3OeNqjG6TINVqGiBKcA1yFQ86Ebg1a2ZBbwU3oiW9UqeGgF9B2t7EgsM436VVkCuxO+X5/9cHUQNdxJriGeYgTjkKMAfajuFCy5qPwTz01tbUmd7vCqSmdLaA6ECPt91CXAI=";
                ;
        String iv = "uv1nVxEWffJfR06qDTnkeA==";
//        Jscode2sessionResult result = jscode2session("071wlbVV0A7em02WSWVV0QbaVV0wlbVK");
//        System.out.println(result);
        String sessionKey = "NlwG3YB/1RR34O5fhNUeSA==";
        decrypt(encryptedData, sessionKey, iv);
    }

    /**
     *
     * @param js_code
     * @throws Exception
     */
    public static Jscode2sessionResult jscode2session(String js_code) throws Exception {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("appid", APP_ID));
        params.add(new BasicNameValuePair("secret", SECRET));
        params.add(new BasicNameValuePair("js_code", js_code));
        params.add(new BasicNameValuePair("grant_type", "authorization_code"));

        URI uri = new URIBuilder(LOGIN_URL).setParameters(params).build();
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity responseEntity = httpResponse.getEntity();
        String resultString = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
        System.out.println(String.format("jscode2session js_code:%s,返回结果:%s ", js_code, resultString));
        Jscode2sessionResult result = JSON.parseObject(resultString, Jscode2sessionResult.class);
        return result;
//        JSONObject result = JSON.parseObject(resultString);
//        Integer errcode = result.getInteger("errcode");
//        if (errcode == null || errcode == 0) {
//            //成功
//            String session_key = result.getString("session_key");
//            String openid = result.getString("openid");
//            LOGGER.info("微信API登录，登录用户：{}", openid);
//            Map<String, Object> otherData = new HashMap<>(1);
//            otherData.put("session_key", session_key);
//            String jwt = JwtUtil.createJWT(openid, "", "", "", 10*60*1000, otherData);
//            return jwt;
//        } else {
//            //失败
//            LOGGER.error("微信API登录出错，错误信息：{}", result);
//            throw new Exception(result.getString("errmsg"));
//        }
    }

    public static void decrypt(String encryptedData, String sessionKey, String iv) {
        String result = "";
        try {
            AES aes = new AES();
            byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
            if(null != resultByte && resultByte.length > 0){
                result = new String(WxPKCS7Encoder.decode(resultByte));
                JSONObject jsonObject = JSONObject.parseObject(result);
                System.out.println("******************");
                System.out.println(jsonObject);
                System.out.println("******************");
                String decryptAppId = jsonObject.getJSONObject("watermark").getString("appid");
                if(!APP_ID.equals(decryptAppId)){
                    result = "";
                }
            }
        } catch (Exception e) {
            result = "";
            e.printStackTrace();
        }
//        return result;
    }
}
