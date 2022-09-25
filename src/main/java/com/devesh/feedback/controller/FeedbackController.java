package com.devesh.feedback.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.devesh.feedback.entity.*;
import com.devesh.feedback.repository.*;
@RestController
public class FeedbackController {
	
	@Autowired
	FeedbackRepository fr;

	List<Feedback> f=new ArrayList<Feedback>();
//	
	@RequestMapping(value="/book",method=RequestMethod.GET)
	public List<Feedback> getResponses(){
		List<Feedback> flist=fr.findAll();
//		if(flist.isEmpty()) {
//			throw new NotFound("No entry in database");
//		}
		return flist;
	}
	
	@RequestMapping(value="/book",method=RequestMethod.POST)
	public Feedback addResponses(@RequestBody(required=false) Feedback m){
		
		if(m!=null) {
			if(m.getName()!=null) {
				return fr.save(m);
			}
			
		}
		return m;
	}
	
	//UPDATE
	@RequestMapping(value="/book",method=RequestMethod.PUT)
	public Feedback updateResponses(@RequestBody(required=false) Feedback bookfeedback){
		Optional<Feedback> b=fr.findById(bookfeedback.getId());
		if(b.isPresent()) {
			return fr.save(bookfeedback);
			
		}
		return bookfeedback;
	}
	
	//DELETE
	@RequestMapping(value="/book/{id}",method=RequestMethod.DELETE)
	public Feedback deleteResponses(@PathVariable("id") int id){
		Optional<Feedback> b=fr.findById(id);
		//if(b.isPresent()) {
			fr.delete(b.get());
			return b.get();
			
		//}
		//return bookfeedback;
	}
	
	
	
}
