package com.taffy.neko.service;


import com.taffy.neko.Result.R;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    R<?> uploadPic(MultipartFile file);
}
