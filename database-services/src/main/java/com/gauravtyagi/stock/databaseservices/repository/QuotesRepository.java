package com.gauravtyagi.stock.databaseservices.repository;

import java.io.Serializable;
import java.util.List;

import com.gauravtyagi.stock.databaseservices.model.Quotes;
import org.springframework.data.jpa.repository.JpaRepository;



public interface QuotesRepository extends JpaRepository<Quotes, Serializable> {

	public  List<Quotes> findByName(String name) ;

}
