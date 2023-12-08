package com.jsp.HomeServo.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jsp.HomeServo.util.ResponseStructure;

@ControllerAdvice
public class ExceptionHandlerClass extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ResponseStructure<String>> SQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage(ex.getMessage());
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setData("you cant perform the operation");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.BAD_REQUEST);
		
		
	}
	
	@ExceptionHandler(EmailNotFoundForCustomerException.class)
	public ResponseEntity<ResponseStructure<String>> emailNotFoundForCustomerException(EmailNotFoundForCustomerException em){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage(em.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("email not Exist");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PasswordNotFoundForCustomerException.class)
	public ResponseEntity<ResponseStructure<String>> passwordNotFoundForCustomerException(PasswordNotFoundForCustomerException pe){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage(pe.getMessage());
		structure.setData("please enter the right password");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoSuchElementFoundByCustomerException.class)
	public ResponseEntity<ResponseStructure<String>> noSuchElementFoundByCustomerException(NoSuchElementFoundByCustomerException ex){
		ResponseStructure<String > structure=new ResponseStructure<>();
		structure.setMessage(ex.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("No Object Found");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
		
	@ExceptionHandler(NoSuchElementFoundByVendorException.class)
	public ResponseEntity<ResponseStructure<String>> noSuchElementFoundByVendorException(NoSuchElementFoundByVendorException ve){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setData("No Object Found");
		structure.setMessage(ve.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EmailNotFoundForVendorException.class)
	public ResponseEntity<ResponseStructure<String>>  emailNotFoundForVendorException(EmailNotFoundForVendorException em){
		ResponseStructure< String> structure=new ResponseStructure<>();
		structure.setMessage(em.getMessage());
		structure.setData("please check your email once again");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		
		return new  ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PasswordNotFoundForVendorException.class)
	public ResponseEntity<ResponseStructure<String>> passwordNotFoundForVendorException(PasswordNotFoundForVendorException pe){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage(pe.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("invalid password");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoSuchElementFoundForWork.class)
	public ResponseEntity<ResponseStructure<String>> noSuchElementFoundForWork(NoSuchElementFoundForWork wo){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage(wo.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("no work is available right now");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(NoSuchElementFoundForCostException.class)
	public ResponseEntity<ResponseStructure<String>> noSuchElementFoundForCostException(NoSuchElementFoundForCostException co){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage(co.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("no cost available right now");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(NoSuchElementFoundFOrAddressException.class)
	public ResponseEntity<ResponseStructure<String>> noSuchElementFoundFOrAddressException(NoSuchElementFoundFOrAddressException ad){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage(ad.getMessage());
		structure.setData("no adress found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		}

}
