package cn.obanks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.obanks.common.Paginate;
import cn.obanks.model.Authority;
import cn.obanks.model.RoleInfo;
import cn.obanks.model.UserInfo;
import cn.obanks.security.web.OBSSOUtils;
import cn.obanks.service.AuthorityService;
import cn.obanks.service.RoleInfoService;
import cn.obanks.service.UserInfoService;

@Controller
@RequestMapping("/authority")
public class AuthorityController {

	@Autowired
	private RoleInfoService roleInfoService;
	@Autowired
	private AuthorityService authorityService;
	@Autowired
	private UserInfoService userInfoService;

	@ModelAttribute
	public void populateItems(Model model) {

	}

	@RequestMapping(method = RequestMethod.GET, value = "grant")
	public String grant(Model model, @RequestParam Long userId) {
		List<Authority> authorities = this.authorityService.getGrantedAuthorities(userId);
		List<RoleInfo> roleInfos = this.roleInfoService.searchAll();
		Authority authority = new Authority();
		for (Authority authority2 : authorities) {
			authority.getRoldIds().add(authority2.getRoleId());
		}
		authority.setUserId(userId);
		model.addAttribute("roleInfos", roleInfos);
		model.addAttribute("authority", authority);
		return "authority/grant";
	}

	@RequestMapping(method = RequestMethod.POST, value = "grant")
	public String grant(Model model, @ModelAttribute Authority authority) {
		Long userId = OBSSOUtils.getUserId();
		this.authorityService.grant(authority, userId);
		return this.grant(model, userId);
	}

	@RequestMapping(method = RequestMethod.GET, value = "search")
	public String search(Model model, @ModelAttribute UserInfo userInfo) {
		return "authority/search";
	}

	@RequestMapping(method = RequestMethod.POST, value = "search")
	public String search(Model model, @ModelAttribute UserInfo userInfo, @ModelAttribute Paginate paginate) {
		paginate = this.userInfoService.search(userInfo, paginate);
		model.addAttribute("paginate", paginate);
		return "authority/list";
	}

}
