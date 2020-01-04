package com.exam.web.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 文件上传
 * @Author konglf
 * @Date 2020/1/4
 */
@Service
public class UploadFileService {

    private static final Logger logger = LoggerFactory.getLogger(UploadFileService.class);

    /**上传地址*/
    @Value("${file.upload.path}")
    private String filePath;

    public String uploadFile(MultipartFile file){
        try {
            if(file == null){
                return "";
            }
            String subFix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String fileName = System.currentTimeMillis()+ subFix;

            File destFile = new File(filePath, fileName);
            // 判断路径是否存在，如果不存在就创建一个
            if (!destFile.getParentFile().exists()) {
                destFile.getParentFile().mkdirs();
            }

            file.transferTo(destFile);
            return fileName;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}