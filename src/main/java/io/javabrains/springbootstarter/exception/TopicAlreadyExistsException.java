package io.javabrains.springbootstarter.exception;

@SuppressWarnings("serial")
public class TopicAlreadyExistsException extends RuntimeException {
	private String message;

	public TopicAlreadyExistsException() {
	}

	public TopicAlreadyExistsException(String msg) {
		super(msg);
		this.message = msg;
	}

}
