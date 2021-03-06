package org.rone.study.java.third.wechat;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;

/**
 * @Author: Rone   2020-04-01 16:29
 */
public class WXCore {


    private static final String WATERMARK = "watermark";
    private static final String APPID = "appid";
    /**
     * 解密数据
     * @return
     * @throws Exception
     */
    public static String decrypt(String appId, String encryptedData, String sessionKey, String iv){
        String result = "";
        try {
            AES aes = new AES();
            byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
            if(null != resultByte && resultByte.length > 0){
                result = new String(WxPKCS7Encoder.decode(resultByte));
                JSONObject jsonObject = JSONObject.parseObject(result);
                System.out.println(jsonObject);
                String decryptAppid = jsonObject.getJSONObject(WATERMARK).getString(APPID);
                if(!appId.equals(decryptAppid)){
                    result = "";
                }
            }
        } catch (Exception e) {
            result = "";
            e.printStackTrace();
        }
        return result;
    }


//    public static void main(String[] args) throws Exception{
//        String appId = "wxxxxxxxxxxxxxx01";
//        String encryptedData = "Zratztnh8F4yAQ3KBIG5tWEGgrcA3E/WGG4kMmdVo2s6T5ti1MlH/4XNzFxrprCZMzXy1TvTsNoWwOuElmiKydt9ipxc60ix9GlfoAKR2mRmpsIEKj2RvfynT3xHNyZ4cYugCHa/iGVVGosj0nHQJ4Agi3745hHYcNSoPw+ZPMqty4LV8X1goILnLIx1vZcBzZt34S+5MIie14hGQ79vtg==";
//        String sessionKey = "CkY36cHwxqKuszJy5VraTFQ==";
//        String iv = "k+lDDrGJkO0Nu2+ThPbhTQ==";
//        System.out.println(decrypt(appId, encryptedData, sessionKey, iv));
//    }
}
