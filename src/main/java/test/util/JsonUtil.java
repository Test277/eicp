package test.util;

import com.google.gson.Gson;
import test.okhttp.IResponse;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class JsonUtil {

    private class ParameterizedTypeImpl implements ParameterizedType{
        private Class c;

        public ParameterizedTypeImpl(Class c){
            this.c = c;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{c};
        }

        @Override
        public Type getRawType() {
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }

    public <T> T parseJsonToObject(String json, Class c){
        Gson g = new Gson();
        return g.fromJson(json, (Type) c);
    }

    public <T> List<T> parseJsonToList(String json, Class c){
        Gson g = new Gson();
        Type t = new ParameterizedTypeImpl(c);
        return g.fromJson(json, t);
    }

    public static void main(String[] arg0){
        String str = "{\n" +
                "    \"code\":200,\n" +
                "    \"msg\":\"SUCCESS\",\n" +
                "    \"data\":{\n" +
                "        \"total\":0,\n" +
                "        \"datas\":[\n" +
                "{a:1, b:2},{a:1,b:2} "+
                "        ],\n" +
                "        \"draw\":0,\n" +
                "        \"start\":1,\n" +
                "        \"page\":1,\n" +
                "        \"limit\":20,\n" +
                "        \"lastPage\":0,\n" +
                "        \"recordsFiltered\":0,\n" +
                "        \"recordsTotal\":0\n" +
                "    },\n" +
                "    \"success\":true\n" +
                "}";
        IResponse response = new Gson().fromJson(str, IResponse.class);
        response.getData();
    }
}
