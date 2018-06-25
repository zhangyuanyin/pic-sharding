package com.gome.util;

import java.util.UUID;

import org.springframework.stereotype.Service;

/**
 * 序号生成器
 */
@Service
public class GeneratorUtil {

    /**
     * 生成32位来源业务流水号，用于短信发送
     * @return
     */
    public static String generateSourceNo(){
        //TODO 暂用UUID生成
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    public static void main(String[] args){
    	System.out.println( String.valueOf(146544400L&((1 << 23)-1)));
    }
}
