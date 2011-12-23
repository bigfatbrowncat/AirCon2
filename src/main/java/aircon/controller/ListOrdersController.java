package aircon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller(value="list")
@RequestMapping("/list")
public class ListOrdersController {

	@RequestMapping(method = RequestMethod.GET)
	public String listOrders(
			@RequestParam(value="customerNameSearchRequest", defaultValue="") String customerNameSearchRequest, 
			@RequestParam(value="productManufacturerAndModelSearchRequest", defaultValue="") String productManufacturerAndModelSearchRequest, 
			@RequestParam(value="targetAddressSearchRequest", defaultValue="") String targetAddressSearchRequest, 
			@RequestParam(value="searchNew", defaultValue="off") String searchNew, 
			@RequestParam(value="searchInspected", defaultValue="off") String searchInspected, 
			@RequestParam(value="searchCompleted", defaultValue="off") String searchCompleted, 
			@RequestParam(value="searchCancelled", defaultValue="off") String searchCancelled, Model model) {

		model.addAttribute("customerNameSearchRequest", customerNameSearchRequest);
		model.addAttribute("productManufacturerAndModelSearchRequest", productManufacturerAndModelSearchRequest);
		model.addAttribute("targetAddressSearchRequest", targetAddressSearchRequest);

		model.addAttribute("searchNew", searchNew.equals("on"));
		model.addAttribute("searchInspected", searchInspected.equals("on"));
		model.addAttribute("searchCompleted", searchCompleted.equals("on"));
		model.addAttribute("searchCancelled", searchCancelled.equals("on"));
		
        return "list";
        
    }
	
/*	@RequestMapping(method = RequestMethod.GET)
	public String listOrders(@ModelAttribute SearchRequestData orderSearch)
	{
		return "list";
		
	}*/
}