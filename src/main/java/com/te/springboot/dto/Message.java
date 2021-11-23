package com.te.springboot.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Message {

	private int statusCode;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date timestamp;
	private boolean error;
	private String message;
	private Object data;

	public Message(boolean error, String message, List<Object> datas) {

		this.error = error;
		this.message = message;
		for (Object object : datas) {
			this.data = object;
		}
	}

	public Message(int statusCode, Date timestamp, boolean error, String message, Object data) {
		this.statusCode = statusCode;
		this.timestamp = timestamp;
		this.error = error;
		this.message = message;
		this.data = data;
	}

}
