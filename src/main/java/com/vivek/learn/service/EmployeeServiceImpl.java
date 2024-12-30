package com.vivek.learn.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import com.vivek.learn.model.Employee;
import com.vivek.learn.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl {
	
	
	private EmployeeRepository employeeRepository;
	
	private EmployeeServiceImpl(EmployeeRepository employeeRepository)
	{
		this.employeeRepository = employeeRepository;
	}
	
	public void saveFileData(InputStream file) throws EncryptedDocumentException, IOException {
		List<Employee> employeeList = new LinkedList<>();

		Workbook workBook = WorkbookFactory.create(file);

		Sheet sheet = workBook.getSheetAt(0);

		sheet.forEach(row -> {
			Employee employee = new Employee();

			if (row.getRowNum() != 0) {

				employee.setEmpName(row.getCell(0).getStringCellValue());
				employee.setEmpSalary(row.getCell(1).getNumericCellValue());
				employeeList.add(employee);
			}
		});

		employeeRepository.saveAll(employeeList);
	}
	
	
	public List<Employee> findAllData()
	{
		return employeeRepository.findAll();
	}
	

}
