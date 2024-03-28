package com.taffy.neko.controller;


import com.taffy.neko.Result.R;
import com.taffy.neko.enums.ResponseEnum;
import com.taffy.neko.service.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/file")
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
