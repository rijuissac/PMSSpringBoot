package com.example.PMS1;

import java.util.List;

public interface PMSRepositoryCustom 
{
	
	List<PMS> findAllByProductName(String productName);
}
