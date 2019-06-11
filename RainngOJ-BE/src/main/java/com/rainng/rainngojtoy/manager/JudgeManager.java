package com.rainng.rainngojtoy.manager;

import com.alibaba.fastjson.JSONObject;
import com.rainng.rainngojtoy.util.Md5Encrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@Component
public class JudgeManager {
    private static JudgeManager instance;

    public static JudgeManager getInstance() {
        return instance;
    }

    @PostConstruct
    public void init() {
        instance = this;
    }

    @Value("${oj.judger.name}")
    private String judgerName;
    @Value("${oj.judger.password}")
    private String judgerPassword;

    public boolean authJudger(JSONObject request) {
        System.out.println(judgerName);

        String name = request.getString("judgerName");
        String token = request.getString("token");

        if ("".equals(name) || "".equals(token)) {
            return false;
        }

        String correctToken = createToken();
        return judgerName.equals(name) && correctToken.equals(token);
    }

    private String createToken() {
        String name = judgerName;
        String secret = judgerPassword;

        String nameSecretMd5 = Md5Encrypt.toHexString(name + secret);

        return Md5Encrypt.toHexString(nameSecretMd5 + getDateString());
    }

    public String getDateString() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        return format.format(date);
    }
}
