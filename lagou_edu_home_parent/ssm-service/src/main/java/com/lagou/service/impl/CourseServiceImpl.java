package com.lagou.service.impl;

import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;
import com.lagou.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;


    @Override
    public List<Course> findCourseByConditioin(CourseVO courseVo) {
        return courseMapper.findCourseByConditioin(courseVo);
    }

    @Override
    public void saveCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {
       //封装课程信息
        Course course=new Course();
        BeanUtils.copyProperties(course,courseVO);
        //补全课程信息
        Date date=new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);
        //保存课程信息
        courseMapper.saveCourse(course);
        //取出课程id
        int courseId = course.getId();

        //封装教师信息
        Teacher teacher=new Teacher();
        BeanUtils.copyProperties(teacher,courseVO);
        //补全教师信息
        teacher.setCourseId(courseId);
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        //保存教师信息
        courseMapper.saveTeacher(teacher);
    }

    @Override
    public CourseVO findCourseById(Integer id) {
        return courseMapper.findCourseById(id);
    }

    @Override
    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {
        //封装课程信息
        Course course=new Course();
        BeanUtils.copyProperties(course,courseVO);
        //补全信息
        Date date=new Date();
        course.setUpdateTime(date);
        courseMapper.updateCourse(course);
        //封装教师信息
        Teacher teacher=new Teacher();
        BeanUtils.copyProperties(teacher,courseVO);
        //补全信息
        teacher.setCourseId(course.getId());
        teacher.setUpdateTime(date);
        courseMapper.updateTeacher(teacher);
    }

    @Override
    public void updateCourseStatus(Integer id,Integer status) {
        Course course=new Course();
        course.setUpdateTime(new Date());
        course.setId(id);
        course.setStatus(status);
        courseMapper.updateCourseStatus(course);
    }

}
