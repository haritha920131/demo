package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

@SpringBootApplication
@RestController
public class DemoApplication {

	@Autowired
	private SampleDao sampleDao;

	@Autowired
	private TestDao testDao;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("hello world");

	}

	@GetMapping(value = "/sampleui")
	public ModelAndView insertSampleUi(){
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("loggedusername","Kamal");
		modelAndView.addObject("loggeduserrole","Manager");

		List<Sample> samples = sampleDao.findAll(Sort.by(Sort.Direction.DESC,"id"));

		modelAndView.addObject("sampledata",samples);

		modelAndView.setViewName("sample");
		return modelAndView;

	}

/*
	@PostMapping(value = "/sample")
	public ResponseEntity<String> insertSample(@RequestPart("name")String name){

		try {
			Sample newSample = new Sample();
			newSample.setId(sampleDao.getMax() +1);
			newSample.setName(name);

			sampleDao.save(newSample);
			return new ResponseEntity<String>("Save Successfully..!", HttpStatus.OK);
		}catch (Exception exception){
			return new ResponseEntity<String>("Save not Sucessfully..!\n"+exception.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}*/
@PostMapping(value = "/sample")
public ResponseEntity<String> insertSample(@RequestBody Sample sample){

	try {

		sampleDao.save(sample);
		return new ResponseEntity<>("Save Successfully..!", HttpStatus.OK);
	}catch (Exception exception){
		return new ResponseEntity<>("Save not Sucessfully..! "+exception.getMessage(),HttpStatus.BAD_REQUEST);
	}
}

}
