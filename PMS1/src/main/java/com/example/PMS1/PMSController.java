package com.example.PMS1;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sun.jvm.hotspot.memory.HeapBlock.Header;

@RestController
public class PMSController {
	
	@Autowired
	PMSRepository repository;
	
	@Autowired
	PMSAddService addservice;
	
	AtomicInteger counter = new AtomicInteger();
	
	@GetMapping("/getpizza")
	public List<PMS> getPizzaByProductName(@RequestParam(name="productname")String productname) 
	{
		
		return repository.findAllByProductName(productname);
		
		
	}

	@GetMapping("/getpizza/{productId}")
	public Optional<PMS> getPizzaByProductNamePath(@PathVariable String productId) 
	{
		
		return repository.findById(productId);
		
	}

	@PutMapping("/updatepizza/{Id}")
	public PMS updatePizza(@PathVariable String Id,@RequestBody PMS request)
	{
		
		PMS updateRecord =repository.findById(Id).get();
		updateRecord.setProduct(request.getProduct());
		updateRecord.setPrice(request.getPrice());
		
		repository.save(updateRecord);
		
		return updateRecord;
	}
	
	
	@DeleteMapping("/deletepizza")
	public Object deletePizza(@RequestBody PMS deleteRecord)
	{
		
		repository.delete(repository.findById(deleteRecord.getId()).get());
		
		return new ResponseEntity("Pizza Record Deleted",HttpStatus.CREATED) ;
	}
	
	
	@DeleteMapping("/deleteAll")
	public ResponseEntity deleteAll()
	{
		
		repository.deleteAll();
		return new ResponseEntity("All Product Record Deleted", HttpStatus.CREATED);
	}
	
	
	
	private Object ResponseEntity(String string, HttpStatus created) {
		// TODO Auto-generated method stub
		return null;
	}

	@PostMapping("/addpizza")
	public ResponseEntity<PMSAddResponse> addPizza(@RequestBody PMS pms)
	{
		
		
		PMSAddResponse response = new PMSAddResponse();
		HttpHeaders header = new HttpHeaders();
		
		String id = pms.getProduct() + counter.getAndIncrement();
		
		if (!addservice.validateExistingRecord(id))
		{
			pms.setId(id);
			repository.save(pms);
			header.add("Unique", id);
			response.setId(id);
			response.setMsg("Success: Product is added");
			
			return new ResponseEntity<>(response, header, HttpStatus.CREATED);
		}
		else
		{
			response.setId(id);
			response.setMsg("Product Already exists");
			
			return new ResponseEntity<PMSAddResponse>(response,HttpStatus.ACCEPTED);
		}
		
		
		
	}
	
}
