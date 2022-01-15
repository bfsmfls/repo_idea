package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {

    /*根据课程id查询章节信息*/
    public List<CourseSection> findSectionAndLesson(Integer courseId);

    /*回显章节对应的课程信息*/
    public Course findCourseByCourseId(Integer courseId);

    /*新增章节*/
    public void  saveSection(CourseSection section);

    /*修改章节*/
    public void updateSection(CourseSection section);

    /*修改章节状态*/
    public void updateSectionStatus(CourseSection section);

    /*新建课时*/
    public void saveLesson(CourseLesson courseLesson);
}
