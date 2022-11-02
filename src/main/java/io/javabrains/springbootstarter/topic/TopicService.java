package io.javabrains.springbootstarter.topic;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.javabrains.springbootstarter.exception.NoSuchTopicExistsException;

@Service
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;

	public List<Topic> getAllTopics(int page, int size) {
		String sortDir = "ASC";
		String sortBy = "topicId";
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(page, size, sort);
		List<Topic> list = topicRepository.findAll(pageable).getContent();
		return list;
	}

	// Method to get customer by Id.Throws
	// NoSuchTopicException for invalid Id

	public Topic getTopicById(int topicId) {

		return topicRepository.findById(topicId)
				.orElseThrow(() -> new NoSuchTopicExistsException("Topic not found with id :" + topicId));
	}

	public Topic getTopicByName(String topicName) {

		return topicRepository.findByName(topicName);
	}

	public String addTopic(Topic topic) {

		topicRepository.save(topic);
		return topic.getName() + " is added";

	}

	public String updateTopic(int topicId, Topic topic) {
		Topic existingTopic = topicRepository.findById(topicId)
				.orElseThrow(() -> new NoSuchTopicExistsException("Topic not found with id :" + topicId));

		existingTopic.setName(topic.getName());
		existingTopic.setDescription(topic.getDescription());
		topicRepository.save(existingTopic);
		return existingTopic.getName() + " is updated";

	}

	public String deleteTopic(int topicId) {
		Topic deleteTopic = topicRepository.findById(topicId)
				.orElseThrow(() -> new NoSuchTopicExistsException("Topic not found with id :" + topicId));
		topicRepository.deleteById(topicId);
		return deleteTopic.getName() + " is deleted";

	}

//	public List<Topic> getTopicPagination(int page, int size) {
//		Pageable pageable = PageRequest.of(page, size);
//		List<Topic> list = topicRepository.findAll(pageable).getContent();
//		return list;
//
//	}
}
