package com.fable.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class AssignmentApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(AssignmentApplication.class, args);
		File myObj = new File("/data/logs.txt");
		if (myObj.createNewFile()) {
			System.out.println("File created: " + myObj.getName());
		} else {
			System.out.println("File already exists.");
		}
	}
}
