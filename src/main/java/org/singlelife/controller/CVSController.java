package org.singlelife.controller;

import java.util.List;

import javax.inject.Inject;

import org.singlelife.service.CVSService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vo.CVSVO;

@RestController
@RequestMapping("/cvs")
public class CVSController {
	
	@Inject
	private CVSService service;
	 
	@RequestMapping("/all")
	public List<CVSVO> listAll(Integer pageNo,Integer pageSize) throws Exception
	{
		return service.listAll(pageNo,pageSize);
	}
	@RequestMapping("/store/{store}")
	public List<CVSVO> listStore(@PathVariable("store") String store,
			Integer pageNo,Integer pageSize) throws Exception
	{
		return service.listStore(store,pageNo,pageSize);
	}
	@RequestMapping("/event/{event}")
	public ResponseEntity<List<CVSVO>> listEvent(@PathVariable("event") String event ,Integer pageNo,Integer pageSize) throws Exception
	{
		ResponseEntity<List<CVSVO>> entity = null;
		if(event.equals("add"))					event = "증정";
		else if(event.equals("sale"))			event = "할인";
		else if(event.equals("plus1"))			event = "1+1";
		else if(event.equals("plus2"))			event = "2+1";
		else 									
		{
			entity = new ResponseEntity<List<CVSVO>>(HttpStatus.BAD_REQUEST);
			return entity;
			}
			
		entity = new ResponseEntity<List<CVSVO>>
		(service.listEvent(event,pageNo,pageSize),HttpStatus.OK);
		return entity;
	}
	@RequestMapping("/{store}/{event}")
	public ResponseEntity<List<CVSVO>> list(@PathVariable("store") String store,
			@PathVariable("event") String event ,Integer pageNo,Integer pageSize) throws Exception
	{
		ResponseEntity<List<CVSVO>> entity = null;
		if(event.equals("add"))					event = "증정";
		else if(event.equals("sale"))			event = "할인";
		else if(event.equals("plus1"))			event = "1+1";
		else if(event.equals("plus2"))			event = "2+1";
		else 									
		{
			entity = new ResponseEntity<List<CVSVO>>(HttpStatus.BAD_REQUEST);
			return entity;
		}
		
		entity = new ResponseEntity<List<CVSVO>>
		(service.list(store,event,pageNo,pageSize),HttpStatus.OK);
		return entity;
	}


}
