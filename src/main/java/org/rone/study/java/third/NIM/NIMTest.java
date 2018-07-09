package org.rone.study.java.third.NIM;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rone on 2018/5/25.
 */
public class NIMTest {

    public static void main(String[] args) throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build();
        String url;
//        url = RequestUtil.URL_FRIEND_GET;
//        url = RequestUtil.URL_USER_GET_UINFOS;
//        url = RequestUtil.URL_USER_SET_SPECIAL_RELATION;
//        url = RequestUtil.URL_USER_LIST_BLACK_AND_MUTE_LIST;
//        url = RequestUtil.URL_MSG_SEND_MSG;
//        url = RequestUtil.URL_TEAM_CREATE;
//        url = RequestUtil.URL_TEAM_QUERY;
        url = RequestUtil.URL_CHATROOM_CREATE;
        HttpPost httpPost = new HttpPost(url);
        RequestUtil.setHeader(httpPost);

        // 设置请求的参数
        List<NameValuePair> paramList = new ArrayList<>();
//        paramList.add(new BasicNameValuePair("", ""));
        //创建聊天室
        paramList.add(new BasicNameValuePair("creator", "T001"));
        paramList.add(new BasicNameValuePair("name", "我的聊天室"));
        //查询群信息一级成员列表
//        JSONArray tidJsons = new JSONArray();
//        tidJsons.add("505492688");
//        paramList.add(new BasicNameValuePair("tids", tidJsons.toString()));
//        paramList.add(new BasicNameValuePair("ope", "1"));
        //创建群, 群id：505492688
//        paramList.add(new BasicNameValuePair("tname", "XX交流群"));
//        paramList.add(new BasicNameValuePair("owner", "T001"));
//        JSONArray membersJsons = new JSONArray();
//        membersJsons.add("T002");
//        membersJsons.add("181034856858259461");
//        paramList.add(new BasicNameValuePair("members", membersJsons.toString()));
//        paramList.add(new BasicNameValuePair("msg", "come on..."));
//        paramList.add(new BasicNameValuePair("magree", "0"));
//        paramList.add(new BasicNameValuePair("joinmode", "0"));
        //发送普通消息
//        paramList.add(new BasicNameValuePair("from", "T001"));
//        paramList.add(new BasicNameValuePair("ope", "0"));
//        paramList.add(new BasicNameValuePair("to", "T002"));
//        paramList.add(new BasicNameValuePair("type", "0"));
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("title", "test");
//        jsonObject.put("msg", "hello world...");
//        paramList.add(new BasicNameValuePair("body", jsonObject.toString()));
        //查看黑名单/静音名单
//        paramList.add(new BasicNameValuePair("accid", "T001"));
        //添加黑名单/静音
//        paramList.add(new BasicNameValuePair("accid", "T001"));
//        paramList.add(new BasicNameValuePair("targetAcc", "181034856862453760"));
//        paramList.add(new BasicNameValuePair("relationType", "1"));
//        paramList.add(new BasicNameValuePair("value", "1"));
        //获取用户名片
//        JSONArray accidJsons = new JSONArray();
//        accidJsons.add("accid_15950");
//        paramList.add(new BasicNameValuePair("accids", accidJsons.toString()));
        //获取朋友accidJsons
//        paramList.add(new BasicNameValuePair("accid", "T001"));
//        paramList.add(new BasicNameValuePair("updatetime", "0"));
        //添加朋友
//        paramList.add(new BasicNameValuePair("accid", "T001"));
//        paramList.add(new BasicNameValuePair("faccid", "T002"));
//        paramList.add(new BasicNameValuePair("type", "1"));
//        paramList.add(new BasicNameValuePair("msg", "hello"));

        httpPost.setEntity(new UrlEncodedFormEntity(paramList, "utf-8"));

        // 执行请求
        HttpResponse response = httpClient.execute(httpPost);

        // 打印执行结果
        System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
    }



}
