package com.taffy.neko.controller;


import com.taffy.neko.Result.R;
import com.taffy.neko.service.FileService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/file")
@Api(tags = "文件模块")
public class FileController {

    @Resource
    private FileService fileService;

    /**
     * 上传图片
     *
     * @param file 文件
     * @return 包含url的obj 回显url存储在数据库方便调用
     */
    @PostMapping("/uploadPic")
    public R<?> uploadPicture(@RequestPart("file") MultipartFile file) {
        return fileService.uploadPic(file);
    }
}
