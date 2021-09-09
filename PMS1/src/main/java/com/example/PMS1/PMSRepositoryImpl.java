package com.example.PMS1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


public class PMSRepositoryImpl implements PMSRepositoryCustom {

	@Autowired
	PMSRepository repository;
	
	@Override
	public List<PMS> findAllByProductName(String productName) 
	{
		
		List<PMS> allRecords =repository.findAll();
		List<PMS> filteredRecords = new ArrayList<PMS>();
		
		for (PMS item : allRecords)
		{
			if (item.getProduct().equals(productName))
			{
				filteredRecords.add(item);
			}
		}
		
		return filteredRecords;
		
	}

}
