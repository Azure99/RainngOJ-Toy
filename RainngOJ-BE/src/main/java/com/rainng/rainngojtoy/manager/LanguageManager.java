package com.rainng.rainngojtoy.manager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class LanguageManager {
    private static LanguageManager instance;

    public static LanguageManager getInstance() {
        return instance;
    }

    @Value("${oj.languages}")
    private String languageString;
    @Value("${oj.dbLanguages}")
    private String dbLanguagesString;

    // example: c=0|c++=1|java=2|python=3
    private Map<Integer, String> langMap = new HashMap<>();
    private Map<Integer, String> dbLangMap = new HashMap<>();

    @PostConstruct
    public void init() {
        instance = this;

        String[] split = languageString.split("\\|");
        for (String item : split) {
            String[] kv = item.split("=");
            langMap.put(Integer.valueOf(kv[1]), kv[0]);
        }

        split = dbLanguagesString.split("\\|");
        for (String item : split) {
            String[] kv = item.split("=");
            dbLangMap.put(Integer.valueOf(kv[1]), kv[0]);
        }
    }

    public Map<Integer, String> getLangMap() {
        return langMap;
    }

    public Map<Integer, String> getDbLangMap() {
        return dbLangMap;
    }

    public String getLangNameById(Integer id) {
        String name = langMap.get(id);
        if (name == null) {
            name = dbLangMap.get(id);
        }
        return name;
    }

    public boolean isDbLang(Integer id) {
        return dbLangMap.containsKey(id);
    }

    public boolean containsLang(Integer id) {
        return langMap.containsKey(id) || dbLangMap.containsKey(id);

    }
}
