package com.exam.websocket;


import com.alibaba.fastjson.JSON;
import com.exam.domain.Notice;


import javax.websocket.*;


public class DecoderClassVo implements Decoder.Text<Notice> {


    @Override
    public Notice decode(String notice) throws DecodeException {

        return JSON.parseObject(notice, Notice.class);
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }
}
