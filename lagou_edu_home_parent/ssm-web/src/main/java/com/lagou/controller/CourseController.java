package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import com.lagou.domain.CourseVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @RequestMapping("/findCourseByConditioin")
    public ResponseResult findCourseByConditioin(@RequestBody CourseVO courseVO){
        List<Course> list = courseService.findCourseByConditioin(courseVO);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", list);
        return result;
    }

    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file")MultipartFile file, HttpServletRequest request) throws IOException {

        //1.判断文件是否为空
        if(file.isEmpty()){
            throw new RuntimeException();
        }
        //2.获取项目部署路径
        //apache-tomcat-8.5.56\webapps\ssm_web\
        String realPath = request.getServletContext().getRealPath("/");
        //apache-tomcat-8.5.56\webapps
        String s = realPath.substring(0, realPath.indexOf("ssm-web"));
        //3.获取原文件名
        String fileName = file.getOriginalFilename();
        //4.新文件名
        //newFileName.jpg
        String newFileName=System.currentTimeMillis()+ fileName.substring(fileName.lastIndexOf("."));
        //5.上传文件
        String uploadPath=s+"upload//";
        File filePath = new File(uploadPath,newFileName);
        //如果目录不存在就创建目录
        if(!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录"+filePath);
        }
        file.transferTo(filePath);
        //6.将文件名和文件路径返回
        Map<String,String> map=new HashMap<>();
        map.put("fileName",newFileName);
        map.put("filePath","http://localhost:8080/upload/"+newFileName);

        ResponseResult result = new ResponseResult(true, 200, "文件上传成功", map);
        return  result;
    }

    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {
        ResponseResult result =null;
        if (courseVO.getId()==null){
            courseService.saveCourseOrTeacher(courseVO);
           result = new ResponseResult(true, 200, "新增成功", null);
        }else {
            courseService.updateCourseOrTeacher(courseVO);
            result = new ResponseResult(true, 200, "更新成功", null);
        }
        return result;
    }
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id){
        CourseVO courseVO = courseService.findCourseById(id);
        ResponseResult result = new ResponseResult(true, 200, "查询成功", courseVO);
        return result;
    }
    @RequestMapping("/updateCourseStatus")
    public ResponseResult  updateCourseStatus(@RequestParam Integer id,@RequestParam Integer status){
        courseService.updateCourseStatus(id, status);
        Map<String,Integer> map=new HashMap<>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
        return result;
    }

}
