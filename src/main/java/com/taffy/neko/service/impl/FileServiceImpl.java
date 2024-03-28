package com.taffy.neko.service.impl;

import cn.hutool.core.date.DateTime;
import com.aliyun.oss.OSSClient;
import com.taffy.neko.Result.R;
import com.taffy.neko.enums.ResponseEnum;
import com.taffy.neko.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;


@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Resource
    private OSSClient ossClient;

    //获取配置文件（yml里的）
    @Resource
    private Environment env;


    @Override
    public R<?> uploadPic(MultipartFile file) {
        //1、创建oss客户端连接
        String url = null;
        try {
            //2、获取文件输入流
            InputStream inputStream = file.getInputStream();
            //3、获取原始文件名
            String originFileName = file.getOriginalFilename();
            //4、防止文件重名
            String uuidFileName = cn.hutool.core.lang.UUID.randomUUID().toString(true) + originFileName;
            //4.1、精确到日
            String dateTime = DateTime.now().toString("yyyy-MM-dd");
            //5、拼接文件名
            String realFileName = dateTime + uuidFileName;
            //拼接dir根目录
            String dirFileName = env.getProperty("aliyun.oss.dir.prefix") + realFileName;
            //6、创建oss请求，传入三个参数
            ossClient.putObject(env.getProperty("aliyun.oss.bucketName"), dirFileName, inputStream);
            //7、拼接图片url路径，方便后续入库
            url = "https://" + env.getProperty("aliyun.oss.bucketName") + "." + env.getProperty("aliyun.oss.endpoint") + "/" + dirFileName;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        if (url != null) {
            return new R<>().success(ResponseEnum.SUCCESS, url);
        } else {
            return new R<>().error(ResponseEnum.ERROR);
        }

    }
}
