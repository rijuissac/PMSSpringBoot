package com.example.PMS1;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PMSAddService 
{
	
	@Autowired
	PMSRepository repository;

	public boolean validateExistingRecord(String id) {
		//System.out.println(repository);
		
		Optional<PMS> record = repository.findById(id);
		
		if (record.isPresent())
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
}
