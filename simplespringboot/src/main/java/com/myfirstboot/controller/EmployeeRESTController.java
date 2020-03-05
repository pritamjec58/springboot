package com.myfirstboot.controller;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myfirstboot.dao.EmployeeDB;
import com.myfirstboot.model.EmployeeListVO;
import com.myfirstboot.model.EmployeeReport;
import com.myfirstboot.model.EmployeeVO;

@RestController
public class EmployeeRESTController {
     
    @RequestMapping(value = "/employees")
    public EmployeeListVO getAllEmployees()
    {
		/*
		 * EmployeeListVO employeesList = new EmployeeListVO();
		 * 
		 * for (EmployeeVO employee : EmployeeDB.getEmployeeList()) {
		 * employeesList.getEmployees().add(employee); }
		 * 
		 * return employeesList;
		 */
    	
    	EmployeeListVO employeesList  = new EmployeeListVO();
    	 
        for (EmployeeVO employee : EmployeeDB.getEmployeeList()) 
        {
            //Adding self link employee 'singular' resource
			
			  Link link = ControllerLinkBuilder .linkTo(EmployeeRESTController.class)
						  .slash("employees")
						  .slash(employee.getEmployeeId())
						  .withSelfRel();
			  //Add link to singular resource
	            employee.add(link);
	            
			/*
			 * ResponseEntity<EmployeeVO> methodLinkBuilder2 = ControllerLinkBuilder
			 * .methodOn(EmployeeRESTController.class).getEmployeeById(employee.
			 * getEmployeeId()); Link reportLink2 = ControllerLinkBuilder
			 * .linkTo(methodLinkBuilder2) .withRel("specific-employee"); employee.add(reportLink2);
			 */
           
             
          //Adding method link employee 'singular' resource
            ResponseEntity<EmployeeReport> methodLinkBuilder = ControllerLinkBuilder
                    .methodOn(EmployeeRESTController.class).getReportByEmployeeById(employee.getEmployeeId());
            Link reportLink = ControllerLinkBuilder
                    .linkTo(methodLinkBuilder)
                    .withRel("employee-report");
     
            //Add link to singular resource
            employee.add(reportLink);
       
            employeesList.getEmployees().add(employee);
        }
         
        //Adding self link employee collection resource
        Link selfLink = ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder
                .methodOn(EmployeeRESTController.class).getAllEmployees())
                .withSelfRel();
     
        //Add link to collection resource
        employeesList.add(selfLink);
          
        return employeesList;
    }
      
    @RequestMapping(value = "/employees/{id}")
    public ResponseEntity<EmployeeVO> getEmployeeById (@PathVariable("id") int id)
    {
		/*
		 * if (id <= 3) { EmployeeVO employee = EmployeeDB.getEmployeeList().get(id-1);
		 * return new ResponseEntity<EmployeeVO>(employee, HttpStatus.OK); } return new
		 * ResponseEntity<EmployeeVO>(HttpStatus.NOT_FOUND);
		 */
    	
    	 if (id <= 3) {
    	        EmployeeVO employee = EmployeeDB.getEmployeeList().get(id-1);
    	         
    	        //Self link
    	        Link selfLink = ControllerLinkBuilder
    	                .linkTo(EmployeeRESTController.class)
    	                .slash(employee.getEmployeeId())
    	                .withSelfRel();
    	         
    	        //Method link
    	        Link reportLink = ControllerLinkBuilder
    	                .linkTo(ControllerLinkBuilder.methodOn(EmployeeRESTController.class)
    	                .getReportByEmployeeById(employee.getEmployeeId()))
    	                .withRel("report");
    	         
    	        employee.add(selfLink);
    	        employee.add(reportLink);
    	        return new ResponseEntity<EmployeeVO>(employee, HttpStatus.OK);
    	    }
    	    return new ResponseEntity<EmployeeVO>(HttpStatus.NOT_FOUND);
    }
     
    @RequestMapping(value = "/employees/{id}/report")
    public ResponseEntity<EmployeeReport> getReportByEmployeeById (@PathVariable("id") int id)
    {
        //Do some operation and return report
        return null;
    }
}