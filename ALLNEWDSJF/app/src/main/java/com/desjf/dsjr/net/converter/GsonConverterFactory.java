/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.desjf.dsjr.net.converter;

import com.desjf.dsjr.net.common.BasicResponseBean;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * 解析返回的json数据，自定义GsonConverterFactory继承自Converter.Factory
 * 然后Retrofit中绑定当前自定义的GsonConverterFactory
 */
public final class GsonConverterFactory extends Converter.Factory {

    public static GsonConverterFactory create() {
        return create(new Gson());
    }

    public static GsonConverterFactory create(Gson gson) {
        return new GsonConverterFactory(gson);
    }
    //将返回的数据解析成Json格式
    com.alibaba.fastjson.JSONObject jsonObject;
    private final Gson gson;

    private GsonConverterFactory(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(final Type type, Annotation[] annotations, Retrofit retrofit) {
        Type newType = new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return new Type[] { type };
            }

            @Override
            public Type getOwnerType() {
                return null;
            }

            @Override
            public Type getRawType() {
                //自定义基础数据类型（服务器返回json格式）
                return BasicResponseBean.class;
            }
        };
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(newType));
        //自定义GsonResponseBodyConverter
        return new GsonResponseBodyConverter<>(adapter);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations,
                                                          Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        //自定义GsonRequestBodyConverter
        return new GsonRequestBodyConverter<>(gson, adapter);
    }
}
