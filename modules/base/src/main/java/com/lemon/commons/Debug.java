package com.lemon.commons;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author bob 北京易智享科技有限公司
 * @version v3 2015年4月29日 下午8:37:03
 */
public class Debug {

    public static final boolean debugOn = true;
    public static boolean keepTemp = false;

    public static boolean isDebug() {
        return debugOn;
    }

    public static boolean isProduct() {
        return !debugOn;
    }

    public static final Map<String, String> Web_Url = new ConcurrentHashMap<>();

    static {
        if (debugOn) {
            Web_Url.put("URL_WEB", "http://10.0.1.9");
//            Web_Url.put("URL_WEB", "http://127.0.0.1:8080");
            Web_Url.put("URL_MEDIA", "http://10.0.1.9:8080");
            //Web_Url.put("URL_MEDIA", "http://localhost:8888");
            Web_Url.put("URL_OFFICE", "http://10.0.1.50");
        } else {
            Web_Url.put("URL_WEB", "http://www.chaojijiangshi.cn");
            Web_Url.put("URL_MEDIA", "http://doc.chaojijiangshi.cn");
            Web_Url.put("URL_OFFICE", "http://office.chaojijiangshi.cn");
        }
        Web_Url.put("URL_STATIC_URL", "http://doc.chaojijiangshi.cn");
    }

    public static class Quest {
        public static long renderDuration = debugOn ? 600L : 400L; //调试期间延迟大点，方便人眼差错；运行期间尽可能小，加速执行。
    }
}