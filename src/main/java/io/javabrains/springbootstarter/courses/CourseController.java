package io.javabrains.springbootstarter.courses;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import io.javabrains.springbootstarter.topic.TopicController;
//import io.javabrains.springbootstarter.topic.TopicRepository;

@RestController
public class CourseController {

	@Autowired
	private CourseService courseService;

	@RequestMapping("/topics/courses")
	public List<Course> getAllCourses(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "6", required = false) int size) {
		return courseService.getAllCourses(page, size);
	}

	@RequestMapping("/topics/{topicId}/courses")
	public List<Course> getTopicCourses(@PathVariable int topicId) {
		return courseService.getTopicCourses(topicId);
	}

	@RequestMapping("/topics/{topicId}/courses/{courseId}")
	public Course getCourse(@PathVariable int courseId) {
		return courseService.getCourse(courseId);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/topics/{topicId}/courses")
	public String addCourse(@RequestBody @Valid Course course, @PathVariable int topicId) {

		return courseService.addCourse(course, topicId);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/topics/{topicId}/courses/{courseId}")
	public String updateCourse(@RequestBody @Valid Course course, @PathVariable int topicId,
			@PathVariable int courseId) {
		// course.setTopic(new Topic(topicId, "",""));
		return courseService.updateCourse(course, courseId, topicId);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/topics/{topicId}/courses/{courseId}")
	public String deleteCourse(@PathVariable int courseId, @PathVariable int topicId) {
		return courseService.deleteCourse(courseId, topicId);
	}

}
