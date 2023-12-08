package com.jsp.HomeServo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.HomeServo.dto.Vendor;
import com.jsp.HomeServo.service.VendorService;
import com.jsp.HomeServo.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class VendorController {
	@Autowired
	VendorService service;
	
	@PostMapping("/vendor")
	public ResponseEntity<ResponseStructure<Vendor>> saveVendor(@RequestBody Vendor vendor) {
		return service.saveVendor(vendor);
	}
	
	@PutMapping("/vendor")
	public ResponseEntity<ResponseStructure<Vendor>> updateVendor(@RequestBody Vendor vendor) {
		return service.updateVendor(vendor);
	}
	
	@GetMapping("/vendor")
	public ResponseEntity<ResponseStructure<Vendor>> getVendorById(@RequestParam int id){
		return service.getVendorById(id);
	}
	
	@GetMapping("/vendor/login")
	public ResponseEntity<ResponseStructure<Vendor>> login(@RequestParam String email,@RequestParam String pwd){
		return service.login(email, pwd);
	}
	
	@DeleteMapping("/vendor")
	public ResponseEntity<ResponseStructure<Vendor>> deleteVendor(@RequestParam int id){
		return service.deleteVendor(id);
	}
	
	@GetMapping("/allvendor")
	public ResponseEntity<ResponseStructure<List<Vendor>>> getAllVendor(@RequestParam int c_id){
		return service.getAllVendor(c_id);
	}

}
