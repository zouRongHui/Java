package org.rone.study.java.tool.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * create by zouRongHui 2018/6/1
 */
public class JwtTest {

    public static void main(String[] args) {
        SecretKey key = gainKey("rone_test");
        Map<String, Object> claims = new HashMap<>(2);
        claims.put("name", "rone");
        claims.put("status", "ready");
        String jwt = initJWT(key, claims, "qianzhui");
        System.out.println(jwt);
        Map paramMap = parseJWT(jwt, gainKey("rone_test"));
        System.out.println(paramMap);
    }

    /**
     * create by zouRongHui 2018/6/1
     * 根据密钥获取jwt
     *
     * @param key
     * @return
     */
    public static String initJWT(SecretKey key, Map claims, String issuer) {
        JwtBuilder builder = Jwts.builder().setClaims(claims)
                //jjwt 已经封装，可省略
//                .setHeaderParam("typ", "JWT")
//                .setHeaderParam("alg", "HS256")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setIssuer(issuer)
                .signWith(SignatureAlgorithm.HS256, key);
        return builder.compact();
    }

    /**
     * create by zouRongHui 2018/6/1
     * 获取密钥
     * @param jwt
     * @param key
     * @return
     */
    public static Map<String, Object> parseJWT(String jwt, SecretKey key) {
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
        return claims;
    }

    /**
     * create by zouRongHui 2018/6/1
     * 解析jwt
     * @param stringKey
     * @return
     */
    public static SecretKey gainKey(String stringKey) {
        byte[] encodeKey = Base64.decodeBase64(stringKey);
        SecretKey key = new SecretKeySpec(encodeKey, 0, encodeKey.length, "AES");
        return key;
    }

}
