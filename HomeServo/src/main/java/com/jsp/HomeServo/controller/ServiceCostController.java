package com.jsp.HomeServo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.HomeServo.dto.ServiceCost;
import com.jsp.HomeServo.service.ServiceCostService;
import com.jsp.HomeServo.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class ServiceCostController {
	@Autowired
	ServiceCostService service;
	
	@PostMapping("/cost")
	public ResponseEntity<ResponseStructure<ServiceCost>> saveCost(@RequestParam int v_id, @RequestParam int w_id){
		return service.saveCost(w_id, v_id);
	}
	
	@PutMapping("/cost")
	public ResponseEntity<ResponseStructure<ServiceCost>> payment(@RequestParam int c_id, @RequestBody ServiceCost cost){
		System.out.println(c_id);
		return service.payment(c_id, cost);
	}
}
