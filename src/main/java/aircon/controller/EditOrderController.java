package aircon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import aircon.model.Order.StateType;

@Controller("editor")
public class EditOrderController {
	@RequestMapping(value="/new", method = RequestMethod.GET)
	public String newOrder(Model model)
	{
		model.addAttribute("state", StateType.STATE_NEW);
		return "editor";
	}

	@RequestMapping(value="/new", method = RequestMethod.POST)
	public String newOrderFilled(
			@RequestParam(value="state", defaultValue="new") String state, 
			
			Model model)
	{
		
		return "editor";
	}
}
