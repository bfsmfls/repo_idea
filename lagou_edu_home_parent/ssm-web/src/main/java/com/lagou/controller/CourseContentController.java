package com.lagou.controller;

import com.lagou.domain.*;
import com.lagou.service.CourseContentService;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLesson(Integer courseId){
        List<CourseSection> list = courseContentService.findSectionAndLesson(courseId);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", list);
        return result;
    }

    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId){
        Course course = courseContentService.findCourseByCourseId(courseId);
        Map<String,Object> map =new LinkedHashMap<>();
        map.put("id",courseId);
        map.put("courseName",course.getCourseName());
        ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
        return result;
    }

    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult  saveOrUpdateSection(@RequestBody CourseSection courseSection){
        ResponseResult result=null;
        if (courseSection.getId()==null){
            courseContentService.saveSection(courseSection);
            result = new ResponseResult(true, 200, "新增章节成功", null);
        }else {
            courseContentService.updateSection(courseSection);
            result = new ResponseResult(true, 200, "修改章节成功", null);
        }
        return result;
    }

    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(Integer id,Integer status){
        courseContentService.updateSectionStatus(id, status);
        Map<String,Integer> map=new HashMap<>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true, 200, "修改章节状态成功", map);
        return result;
    }

    @RequestMapping("/saveLesson")
    public ResponseResult saveLesson(@RequestBody CourseLesson courseLesson){
        courseContentService.saveLesson(courseLesson);
        ResponseResult result = new ResponseResult(true, 200, "新增课时成功", null);
        return result;
    }


}
