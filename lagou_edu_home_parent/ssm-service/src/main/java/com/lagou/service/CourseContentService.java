package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentService {
    public List<CourseSection> findSectionAndLesson(Integer courseId);

    public Course findCourseByCourseId(Integer id);

    public void  saveSection(CourseSection section);
    public void updateSection(CourseSection section);

    public void updateSectionStatus(Integer id,Integer status);

    public void saveLesson(CourseLesson courseLesson);
}
