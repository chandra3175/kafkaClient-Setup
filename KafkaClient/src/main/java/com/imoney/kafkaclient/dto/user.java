package com.imoney.kafkaclient.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class user {
	private Long id;
	private String name;
	private String rollNumber;
}
