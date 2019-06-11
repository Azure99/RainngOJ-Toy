package com.rainng.rainngojtoy;

import com.rainng.jerry.JerryBuilder;
import com.rainng.jerry.mouse.HttpServer;
import com.rainng.jerry.mouse.http.HttpContext;
import com.rainng.jerry.mouse.http.constant.RequestMethod;
import com.rainng.jerry.mouse.http.map.HttpHeaderMap;
import com.rainng.jerry.mouse.middleware.BaseMiddleware;
import com.rainng.jerry.webapi.Controller;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class JerryStarter implements ApplicationContextAware {
    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Value("${server.port:8080}")
    private Integer port;
    @Value("${server.wwwroot:wwwroot}")
    private String wwwroot;

    @PostConstruct
    public void startJerryHost() {
        System.out.println("《《《Jerry Framework》》》");
        Class[] controllers = scanControllers();

        for (Class controller : controllers) {
            System.out.println("Controller: " + controller.getSimpleName());
        }

        System.out.println("Start Jerry mouse host...");
        new Thread(() -> {
            createHttpServer(controllers).start();
        }).start();
    }

    private HttpServer createHttpServer(Class[] controllers) {
        JerryBuilder builder = JerryBuilder.createWebApi(controllers);
        builder.setPort(port).setRootDirectory(wwwroot);

        HttpServer server = builder.build();
        server.addMiddleware(new BaseMiddleware() {
            @Override
            public void onExecute(HttpContext context) throws Exception {
                setCORS(context);
                if (context.getRequest().getMethod() == RequestMethod.OPTIONS) {
                    context.getResponse().setStatusCode(200);
                    return;
                }
                next(context);
            }
        });

        return server;
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

    private Class[] scanControllers() {
        Map<String, Controller> controllerMap = context.getBeansOfType(Controller.class);
        Class[] controllers = new Class[controllerMap.values().size()];

        int index = 0;
        for (Controller controller : controllerMap.values()) {
            controllers[index++] = controller.getClass();
        }

        return controllers;
    }
}
