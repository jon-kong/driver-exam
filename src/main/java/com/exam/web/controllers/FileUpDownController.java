package com.exam.web.controllers;

import com.exam.web.services.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description TODO
 * @Author konglf
 * @Date 2020/1/4
 */
@Controller
@RequestMapping("/fileUpDown")
public class FileUpDownController {

    @Autowired
    private UploadFileService uploadFileService;

    /**上传地址*/
    @Value("${file.upload.path}")
    private String filePath;

    @PostMapping("/upload")
    public String fileUpload(
            @RequestParam("file") MultipartFile file, HttpServletRequest req, Model model){
        String fileName = uploadFileService.uploadFile(file);
        model.addAttribute("fileName",fileName);
        return null;
    }
}