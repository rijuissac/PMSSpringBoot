package com.example.PMS1;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


public interface PMSRepository extends JpaRepository<PMS, String>, PMSRepositoryCustom{


}
