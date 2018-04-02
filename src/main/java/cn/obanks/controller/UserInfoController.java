package cn.obanks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cn.obanks.common.Paginate;
import cn.obanks.model.UserInfo;
import cn.obanks.service.UserInfoService;

@Controller
@RequestMapping("/userinfo")
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping(method = RequestMethod.GET, value = "search")
	public String search(Model model, @ModelAttribute UserInfo userInfo) {
		return "userinfo/search";
	}

	@RequestMapping(method = RequestMethod.POST, value = "search")
	public String search(Model model, @ModelAttribute UserInfo userInfo, @ModelAttribute Paginate paginate) {
		paginate = this.userInfoService.search(userInfo, paginate);
		model.addAttribute("paginate", paginate);
		return "userinfo/list";
	}

}
