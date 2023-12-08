package com.jsp.HomeServo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.HomeServo.dto.Work;
import com.jsp.HomeServo.service.WorkService;
import com.jsp.HomeServo.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE,RequestMethod.PATCH})
public class WorkController {
	@Autowired
	WorkService service;
	
	@PostMapping("/work")
	public ResponseEntity<ResponseStructure<Work>> saveWork(@RequestBody Work work,@RequestParam int cus_id){
		return service.saveWork(work, cus_id);
	}
	
	@GetMapping("/work")
	public ResponseEntity<ResponseStructure<Work>> getWorkById(@RequestParam int id){
		return service.getWorkById(id);
	}
	
	@PutMapping("/start")
	public ResponseEntity<ResponseStructure<Work>> startDate(@RequestParam int w_id, @RequestParam int  v_id){
		return service.startDate(w_id, v_id);
	}
	
	@PutMapping("/end")
	public ResponseEntity<ResponseStructure<Work>> endDate(@RequestParam int w_id,@RequestParam int v_id){
		return service.endDate(w_id, v_id);
	}
	
	@GetMapping("/allwork")
	public ResponseEntity<ResponseStructure<List<Work>>> getAllWorks(@RequestParam int v_id){
		return service.listOfWork(v_id);
	}
	
	@GetMapping("work/ongoingworks")
	public ResponseEntity<ResponseStructure<List<Work>>> ongoingWorks(int v_id){
		return service.ongoingWorks(v_id);
	}
	
	
	@GetMapping("work/completedworks")
	public ResponseEntity<ResponseStructure<List<Work>>> completedWorks(int v_id){
		return service.completedWorks(v_id);
	}
	

}
