package com.fable.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@SpringBootApplication
public class AssignmentApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(AssignmentApplication.class, args);
	}
}
