package com.taffy.neko.utils;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.taffy.neko.Exception.ServiceException;

import com.taffy.neko.enums.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class LangChainSDK {

    public String chatWithAI(String content) {
        try {
            // 设置URL
            URL url = new URL("http://127.0.0.1:7861/chat/knowledge_base_chat");
            BufferedReader in = getBufferedReader(url, content);
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            String jsonStr = response.toString().replace("data:", "");
            // 打印响应
            System.out.println(jsonStr);
            //返回
            JSONObject jsonObject = JSONUtil.parseObj(jsonStr);
            return jsonObject.getStr("answer");
        } catch (Exception e) {
            log.error("异常! : {}", e.getMessage());
            throw new ServiceException(ResponseEnum.ERROR);
        }
    }

    private static BufferedReader getBufferedReader(URL url, String query) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // 设置请求方法为POST
        connection.setRequestMethod("POST");
        // 设置请求头
        connection.setRequestProperty("Content-Type", "application/json");
        // 允许输出流
        connection.setDoOutput(true);
        // 创建请求体数据
        String requestBody = "{\"query\":\"" + query + "\",\"knowledge_base_name\":\"hbut\",\"top_k\":3,\"score_threshold\":2,\"stream\":false,\"model_name\":\"qwen-api\",\"temperature\":0.7,\"max_tokens\":0,\"prompt_name\":\"default\"}";
        // 获取输出流并写入请求体数据
        OutputStream os = connection.getOutputStream();
        byte[] input = requestBody.getBytes(StandardCharsets.UTF_8);
        os.write(input, 0, input.length);
        // 获取响应
        return new BufferedReader(new InputStreamReader(connection.getInputStream()));
    }
}
