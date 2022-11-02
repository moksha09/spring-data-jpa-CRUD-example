package io.javabrains.springbootstarter.courses;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.javabrains.springbootstarter.exception.NoSuchTopicExistsException;
import io.javabrains.springbootstarter.topic.Topic;
import io.javabrains.springbootstarter.topic.TopicRepository;

@Service
@Transactional
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private TopicRepository topicRepository;
	

	public List<Course> getAllCourses(int page, int size){
		
		String sortDir = "ASC";
		String sortBy = "courseId";
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
		 Pageable pageable = PageRequest.of(page, size, sort);
		 List<Course> courses = new ArrayList<>();
		 courseRepository.findAll(pageable).forEach(courses::add);
		 return courses;
		 
	 }
	
	 public List<Course> getTopicCourses(int topicId){
		 List<Course> courses = new ArrayList<>();
		 courseRepository.findByTopicTopicId(topicId).forEach(courses::add);
		 return courses;
	 }
	 
	 public Course getCourse(int courseId) {
		 
		 return courseRepository.findById(courseId).orElseThrow(() -> new NoSuchTopicExistsException("Course not found with  :" + courseId));
		 
	 }

	public String addCourse(Course course, int topicId) {
		Topic existingTopic=topicRepository.findById(topicId).orElseThrow(() -> new NoSuchTopicExistsException("Topic not found with id :" + topicId));
		course.setTopic(existingTopic);
		courseRepository.save(course);
		return course.getName()+" is added";
	}

	public String updateCourse(Course course,int courseId, int topicId) {
		Topic updateTopic=topicRepository.findById(topicId).orElseThrow(() -> new NoSuchTopicExistsException("Topic not found with id :" + topicId));
		Course existingCourse = courseRepository.findById(courseId).orElseThrow(() -> new NoSuchTopicExistsException("Course not found with id :" + courseId));
		existingCourse.setName(course.getName());
		existingCourse.setDescription(course.getDescription());
		existingCourse.setTopic(updateTopic);
		courseRepository.save(existingCourse);
		return existingCourse.getName() +" is updated";
		
	}

	public String deleteCourse(int courseId, int topicId) {
		Topic deleteTopic=topicRepository.findById(topicId).orElseThrow(() -> new NoSuchTopicExistsException("Topic not found with id :" + topicId));
		Course courseToDelete = courseRepository.findById(courseId).orElseThrow(() -> new NoSuchTopicExistsException("Course not found with id :" + courseId));
		courseRepository.deleteByCourseId(courseId);
		return "Course is deleted.";
	}
}
