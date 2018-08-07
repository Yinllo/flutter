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

import com.desjf.dsjr.bean.LoginBean;
import com.desjf.dsjr.net.common.BasicResponseBean;
import com.desjf.dsjr.net.exception.ServerResponseException;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 服务器返回数据处理：如果失败，拦截异常信息进行处理；否则返回请求成功的数据信息
 * @param <T>
 */
final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, Object> {

    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public Object convert(ResponseBody value) throws IOException {
        try {
            //将数据转换成定义的基础返回格式
//            com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(value.string());
            BasicResponseBean response = (BasicResponseBean) adapter.fromJson(value.charStream());
             if (response.getStatus().equals("true")) {
                //获取数据成功返回数据
//                if(response.getData()!=null)
                return response.getData();
//                else throw new ServerNoDataResponseException();
             }else if (response.getStatus().equals("false")) {
                 //如果success=false 并且data为空  即获取数据失败 则抛出异常
                // 特定 API 的错误，在相应的 DefaultObserver 的 onError 的方法中进行处理
                throw new ServerResponseException(response.getCode(), response.getMessage());
             }else if(response.getCode().equals("")){
                 //判断token是否过期

             }else if(response.getCode().equals("2602")){
                 //特须情况，登录错误，剩余错误次数  你还有n此尝试机会
                 LoginBean loginBean= (LoginBean) response.getData();
                 String msg="账户或密码输入错误,您还剩余"+ loginBean.getRestLoginTime()+"次尝试机会";
                 throw new ServerResponseException(response.getCode(), msg);
             }
        } finally {
            value.close();
        }
        return null;
    }
}
