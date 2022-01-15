package com.lagou.service.impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
@Service
public class CourseContentServiceImpl implements CourseContentService {
    @Autowired
    private CourseContentMapper courseContentMapper;

    @Override
    public List<CourseSection> findSectionAndLesson(Integer courseId) {
        return courseContentMapper.findSectionAndLesson(courseId);
    }

    @Override
    public Course findCourseByCourseId(Integer id) {
        return courseContentMapper.findCourseByCourseId(id);
    }

    @Override
    public void saveSection(CourseSection section) {
        Date date=new Date();
        section.setCreateTime(date);
        section.setUpdateTime(date);
        courseContentMapper.saveSection(section);
    }

    @Override
    public void updateSection(CourseSection section) {
        Date date=new Date();
        section.setUpdateTime(date);
        courseContentMapper.updateSection(section);
    }

    @Override
    public void updateSectionStatus(Integer id, Integer status) {
        CourseSection section=new CourseSection();
        section.setId(id);
        section.setUpdateTime(new Date());
        section.setStatus(status);
        courseContentMapper.updateSectionStatus(section);
    }

    @Override
    public void saveLesson(CourseLesson courseLesson) {
        Date date=new Date();
        courseLesson.setCreateTime(date);
        courseLesson.setUpdateTime(date);
        courseContentMapper.saveLesson(courseLesson);
    }
}
