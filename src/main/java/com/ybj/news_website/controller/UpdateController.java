package com.ybj.news_website.controller;

import com.ybj.news_website.mapper.UserMapper;
import com.ybj.news_website.util.FileUtil;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@Controller
public class UpdateController {
    @Autowired
    UserMapper mapper;

    @RequestMapping(value="/testuploadimg", method = RequestMethod.POST)
    public @ResponseBody
    String uploadImg(@RequestParam("file") MultipartFile file,
                     HttpServletRequest request, HttpSession session) throws IOException {
        String contentType = file.getContentType();
        //获取文件名
        String fileName = file.getOriginalFilename();
        //把文件名替换为随机字符串，然后文件名.号后缀保留不变
        String newname= UUID.randomUUID().toString()+
                fileName.substring(fileName.lastIndexOf("."));
        System.out.println("名字"+":"+newname);
        /*System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);*/
        String filePath = request.getSession().getServletContext().getRealPath("/");
        String t_user_account=session.getAttribute("t_user").toString();
//        filePath+newname 路径加名字
        String t_user_img=file.getOriginalFilename();
        String t_user_url=filePath+newname;
//        mapper.userhomeimg(t_user_img,t_user_url,t_user_account);
        try {
            FileUtil.uploadFile(file.getBytes(),filePath,newname);
//            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            // TODO: handle exception
        }
        //返回json
        return "uploadimg success";
    }

}
