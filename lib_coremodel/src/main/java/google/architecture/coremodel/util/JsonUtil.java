package google.architecture.coremodel.util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.List;

/**
 * Created by dxx on 2017/11/20.
 */

public class JsonUtil {

    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = new Gson();
        }
    }

    public static <T> T Str2JsonBean(String json, Class<T> clazz) {
        T bean = null;
        if (null != gson) {
            try {
                bean = gson.fromJson(json, clazz);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        }
        return bean;
    }


    public static String JsonBean2Str(Object object) {
        String str = null;
        if (null != gson) {
            try {
                str = gson.toJson(object);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    public static String JsonList2Str(List list) {
        String str = null;
        if (null != gson && list.size() > 0) {
            str = "[";
            for (int i = 0; i < list.size(); i++) {
                if (i != list.size() - 1) {
                    str += gson.toJson(list.get(i)) + ",";
                }else if (i == list.size() - 1){
                    str +=  gson.toJson(list.get(i)) + "]";
                }
            }
        }
        return str;
    }
}
