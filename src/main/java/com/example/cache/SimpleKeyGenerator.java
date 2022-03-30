package com.example.cache;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKey;

import java.lang.reflect.Method;

public class SimpleKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object target, Method method, Object... params) {
        return makeKey(params);
    }

    public static Object makeKey(Object... params){
        if(params.length==0){
            return SimpleKey.EMPTY;
        }
        else{
            return params[0];
        }
    }
}
