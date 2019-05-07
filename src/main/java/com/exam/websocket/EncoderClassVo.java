package com.exam.websocket;


import com.alibaba.fastjson.JSON;
import com.exam.domain.Notice;
import com.exam.utlis.ResultModel;
import org.codehaus.jackson.map.ObjectMapper;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.io.IOException;
import java.util.Map;

public class EncoderClassVo implements Encoder.Text<ResultModel> {

    @Override
    public String encode(ResultModel resultModel) throws EncodeException {

        String StringNo=JSON.toJSONString(resultModel);

        System.out.println("后台向前端发送"+StringNo);

        return StringNo;
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }
}
