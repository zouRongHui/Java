package org.rone.study.java.third.fastJson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.rone.study.java.entity.User;

import java.util.HashMap;
import java.util.Map;


public class FastJsonTest {
    public static void main(String[] args) {
        newObject();
//        object2Json();
//        json2Object();
    }

    /**
     * Json转Object
     */
    public static void json2Object() {
        JSONObject userJson = new JSONObject();
        userJson.put("name", "rone");
        userJson.put("sex", "man");
        userJson.put("age", "21");
        userJson.put("job", "programmer");
        User user = JSON.parseObject(userJson.toJSONString(), User.class);
        System.out.println(user);//User{name='rone', sex='man', age=21, job='programmer', address='null'}
    }

    /**
     * Object转Json
     */
    public static void object2Json() {
        User user = new User("rone", "man", 21, "programmer", "浙江杭州");
        //这里貌似没有Object转JsonObject的方法，可能原因是设计者认为Object比起JsonObject更为方便所以没必要提供这个方法
        String userJsonString = JSON.toJSONString(user);
        System.out.println(userJsonString);//{"address":"浙江杭州","age":21,"job":"programmer","name":"rone","sex":"man"}

    }

    /**
     * 创建一个Json对象
     */
    public static void newObject() {
        //新建一个json对象
        JSONObject json = new JSONObject();
        //添加属性，类似Map
        json.put("name", "rone");
        json.put("sex", "man");
        //转换成String格式
        System.out.println(json.toJSONString());// {"sex":"man","name":"rone"}

        Map<String, Object> map = new HashMap<>(3);
        map.put("first", "hello");
        map.put("second", "the");
        map.put("third", "world");
        //支持已某个Map为引创建
        JSONObject mapJson = new JSONObject(map);
        System.out.println(mapJson.toJSONString());//{"third":"world","first":"hello","second":"the"}
    }
}
