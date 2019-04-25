package com.ybj.news_website.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

@Controller
public class WebUploaderController {
    @RequestMapping("/webuploader")
    @ResponseBody
    public Map studentImgFileUpload(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
        //获取文件名
        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);
//        String realPath = request.getServletContext().getRealPath("/upload/");
        String realPath = "D:\\picpath";
//        String realPath = System.getProperties().getProperty("user.dir") + "\\src\\main\\resources\\static\\upload\\" ;
//        String realPath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/public/webuploadimage/";

        System.out.println(realPath);
                File file1=new File(realPath);
        if (!file1.exists()){
            file1.mkdirs();
        }
        String uploadFileName = System.currentTimeMillis()+"_"+ originalFilename;
        System.out.println(realPath+uploadFileName);
        boolean flag = true;

        //合并文件
        RandomAccessFile raFile = null;
        BufferedInputStream inputStream = null;
        try{
            File dirFile = new File(realPath, uploadFileName);
            //以读写的方式打开目标文件
            raFile = new RandomAccessFile(dirFile, "rw");
            raFile.seek(raFile.length());
            inputStream = new BufferedInputStream(file.getInputStream());
            byte[] buf = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buf)) != -1) {
                raFile.write(buf, 0, length);
            }
        }catch(Exception e){
            flag = false;
            System.out.println("上传出错:" + e.getMessage());
            throw new IOException(e.getMessage());
        }finally{
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (raFile != null) {
                    raFile.close();
                }
            }catch(Exception e){
                flag = false;
                System.out.println("上传出错:" + e.getMessage());
                throw new IOException(e.getMessage());
            }
        }
        Map map=new HashMap();
        map.put("jsonrpc","2.0");
        map.put("result",null);
        map.put("id",uploadFileName);
        return map;
    }
}
