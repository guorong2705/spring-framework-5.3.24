package com.guorong.demo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable {
	private String name;
	private Integer age;
}
