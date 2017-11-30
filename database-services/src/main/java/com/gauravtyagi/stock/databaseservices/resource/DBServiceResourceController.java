package com.gauravtyagi.stock.databaseservices.resource;

import java.util.List;
import java.util.stream.Collectors;

import com.gauravtyagi.stock.databaseservices.model.Quotes;
import com.gauravtyagi.stock.databaseservices.model.QuotesUser;
import com.gauravtyagi.stock.databaseservices.repository.QuotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("rest/db")
public class DBServiceResourceController {

	@Autowired
    private QuotesRepository quotesRepository	;

	@GetMapping("/{username}")
	   public List<String> getQuotes(@PathVariable("username")   final String userName)
	   {
		 return getQuotesByName(userName);
	   }

	   @PostMapping("/add")
	   public List<String> addQuotes(@RequestBody final QuotesUser quotesUser)
	   {
		   quotesUser.getQuotes()
				   .stream()
				   .forEach(quotes -> quotesRepository.save(new Quotes(quotesUser.getUserName(),quotes)));
		   return getQuotesByName(quotesUser.getUserName());
	   }


	@PostMapping("/delete/{username}")
	public Boolean delete(@PathVariable("username" ) final String userName)
	{
		List<Quotes> list= quotesRepository.findByName(userName);
		quotesRepository.delete(list);
		return Boolean.TRUE;
	}


	   private List<String> getQuotesByName(String userName)
	   {
		   List<Quotes> list= quotesRepository.findByName(userName);
		   return list.stream()
				   .map(Quotes::getQuotes)
				   .collect(Collectors.toList());
	   }
	
	}

