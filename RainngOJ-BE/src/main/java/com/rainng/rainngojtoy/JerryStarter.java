package com.rainng.rainngojtoy;

import com.rainng.jerry.JerryBuilder;
import com.rainng.jerry.mouse.http.HttpContext;
import com.rainng.jerry.mouse.http.constant.HttpMethod;
import com.rainng.jerry.mouse.http.map.HttpHeaderMap;
import com.rainng.jerry.mouse.middleware.BaseMiddleware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JerryStarter {
    @Value("${server.port:8080}")
    private Integer port;
    @Value("${server.wwwroot:wwwroot}")
    private String wwwroot;

    public void startJerryHost() {
        System.out.println("《《《Jerry Framework》》》");
        System.out.println("Start Jerry mouse host...");
        JerryBuilder.createMvc(Application.class)
                .setPort(port)
                .setRootDirectory(wwwroot)
                .build()
                .addMiddleware(new BaseMiddleware() {
                    @Override
                    public void onExecute(HttpContext context) throws Exception {
                        setCORS(context);
                        if (context.getRequest().getMethod() == HttpMethod.OPTIONS) {
                            context.getResponse().setStatusCode(200);
                            return;
                        }
                        next(context);
                    }
                })
                .start();
    }

    private void setCORS(HttpContext context) {
        String origin = context.getRequest().getHeaders().get("Origin", "");
        HttpHeaderMap headers = context.getResponse().getHeaders();
        headers.set("Access-Control-Allow-Origin", origin);
        headers.set("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE");
        headers.set("Access-Control-Allow-Headers", "content-type");
        headers.set("Access-Control-Allow-Credentials", "true");

        try {
            // Thread.sleep(200);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
