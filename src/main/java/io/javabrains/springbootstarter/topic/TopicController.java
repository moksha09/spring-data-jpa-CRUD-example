package io.javabrains.springbootstarter.topic;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {

	@Autowired
	private TopicService topicService;

	@RequestMapping("/topics")
	public List<Topic> getAllTopics(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "3", required = false) int size) {
		return topicService.getAllTopics(page, size);
	}

	@RequestMapping("/topics/{topicId}")
	public Topic getTopicById(@PathVariable int topicId) {
		return topicService.getTopicById(topicId);
	}

	@RequestMapping("/topics/:{topicName}")
	public Topic getTopicByName(@PathVariable String topicName) {
		return topicService.getTopicByName(topicName);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/topics")
	public String addTopic(@RequestBody @Valid Topic topic) {
		return topicService.addTopic(topic);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/topics/{topicId}")
	public String updateTopic(@RequestBody @Valid Topic topic, @PathVariable int topicId) {
		return topicService.updateTopic(topicId, topic);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/topics/{topicId}")
	public String deleteTopic(@PathVariable int topicId) {
		return topicService.deleteTopic(topicId);
	}
}
