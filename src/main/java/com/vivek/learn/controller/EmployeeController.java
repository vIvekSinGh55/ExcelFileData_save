package com.vivek.learn.controller;

import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vivek.learn.model.Employee;
import com.vivek.learn.service.EmployeeServiceImpl;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	
	@Autowired
	private EmployeeServiceImpl empServiceImpl;
	
	
	@PostMapping(value= "/upload", consumes = "multipart/form-data")
	public ResponseEntity<String> saveFileData(@RequestParam("file") MultipartFile file) throws EncryptedDocumentException, IOException{
		
		empServiceImpl.saveFileData(file.getInputStream());
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Excel file data saved into database");
		
	}
	
	@GetMapping("/read-data")
	public ResponseEntity<List<Employee>> findAll()
	{
		return ResponseEntity.ok(empServiceImpl.findAllData());
	}
	
}
