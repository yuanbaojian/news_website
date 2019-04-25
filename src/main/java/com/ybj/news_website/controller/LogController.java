package com.ybj.news_website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
public class LogController {
    @RequestMapping("/adminlog")
    public String log() throws IOException {
        File file =new File("C:\\Users\\向晓，\\logs\\hpaasvc\\warn.log");
        FileInputStream fis =new FileInputStream(file);
        File file2 =new File("C:\\Users\\向晓，\\Desktop\\毕设项目\\lm-cms\\src\\main\\java\\com\\lm\\cms2\\aspect\\log.txt");
        FileOutputStream fos =new FileOutputStream(file2);
        int i =0 ;
        while((i = fis.read())!=-1){
            //i 就是从文件中读取的字节，读完后返回-1
            fos.write(i);
        }
        //4.关闭流
        fis.close();
        return "app/log.html";

    }
}
