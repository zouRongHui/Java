package org.rone.study.java.third.NIM;

import org.apache.http.client.methods.HttpPost;

import java.security.MessageDigest;
import java.util.Random;

/**
 * Created by rone on 2018/5/25.
 * NIM的 Http Request Header 数据工具
 */
public class RequestUtil {

    public static final String APP_KEY = "f07cc14d151f394debb5086ac64cea9d";
    public static final String APP_SECRET = "af49930b40d6";

    /* 网易云信服务端URL */
    private static final String URL = "https://api.netease.im/nimserver";
    /* 新增账号 */
    public static final String URL_ACCID_ADD = URL + "/user/create.action";
    /* 添加朋友 */
    public static final String URL_FRIEND_ADD = URL + "/friend/add.action";
    /* 查询朋友 */
    public static final String URL_FRIEND_GET = URL + "/friend/get.action";
    /* 获取用户名片 */
    public static final String URL_USER_GET_UINFOS = URL + "/user/getUinfos.action";
    /* 设置黑名单/静音 */
    public static final String URL_USER_SET_SPECIAL_RELATION = URL + "/user/setSpecialRelation.action";
    /* 查看指定用户的黑名单和静音列表 */
    public static final String URL_USER_LIST_BLACK_AND_MUTE_LIST = URL + "/user/listBlackAndMuteList.action";
    /* 发送普通消息 */
    public static final String URL_MSG_SEND_MSG = URL + "/msg/sendMsg.action";
    /* 创建群 */
    public static final String URL_TEAM_CREATE = URL + "/team/create.action";
    /* 查询群信息一级成员列表 */
    public static final String URL_TEAM_QUERY = URL + "/team/query.action";
    /* 创建聊天室 */
    public static final String URL_CHATROOM_CREATE = URL + "/chatroom/create.action";

    /**
     * 设置请求的header
     *
     * @param httpPost  需要设值的POST请求对象
     */
    public static void setHeader(HttpPost httpPost) {
        String nonce =  RequestUtil.getNonce();
        String curTime = String.valueOf(System.currentTimeMillis() / 1000L);

        httpPost.addHeader("AppKey", RequestUtil.APP_KEY);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", RequestUtil.getCheckSum(RequestUtil.APP_SECRET, nonce ,curTime));
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
    }

    /**
     * 计算并获取CheckSum
     *
     * @param appSecret
     * @param nonce
     * @param curTime
     * @return
     */
    public static String getCheckSum(String appSecret, String nonce, String curTime) {
        return encode("sha1", appSecret + nonce + curTime);
    }

    /**
     * 计算并获取md5值
     *
     * @param requestBody
     * @return
     */
    public static String getMD5(String requestBody) {
        return encode("md5", requestBody);
    }

    /**
     * 获取随机字符串
     *
     * @return
     */
    public static String getNonce() {
        StringBuilder buffer = new StringBuilder("1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM");
        StringBuilder buffer2 = new StringBuilder();
        Random random = new Random();
        int len = buffer.length();
        for (int i = 0; i < random.nextInt(128) + 1; i++) {
            buffer2.append(buffer.charAt(random.nextInt(len)));
        }

        return buffer2.toString();
    }

    private static String encode(String algorithm, String value) {
        if (value == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(value.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        for (int i = 0; i < len; i++) {
            buf.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return buf.toString();
    }

    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'};
}
