package qianye.pda.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class homeController {

	@RequestMapping("homeData/index")
	public String homeDataIndex(){
		return "home/homeindex";
	}
}
