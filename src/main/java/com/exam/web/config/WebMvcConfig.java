package com.exam.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 图片绝对地址与虚拟地址映射
 * @Author konglf
 * @Date 2020/1/4
 */
@Configuration
public class WebMvcConfig  extends WebMvcConfigurerAdapter {

    @Value("${file.upload.staticAccessPath}")
    private String staticAccessPath;
    @Value("${file.upload.path}")
    private String uploadFolder;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(staticAccessPath).addResourceLocations("file:" + uploadFolder);
    }

}