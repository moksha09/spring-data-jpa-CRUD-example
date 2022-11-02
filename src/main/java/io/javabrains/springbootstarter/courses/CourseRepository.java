package io.javabrains.springbootstarter.courses;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {

	List<Course> findByTopicTopicId(int topicId);

	void deleteByCourseId(int courseId);
}
