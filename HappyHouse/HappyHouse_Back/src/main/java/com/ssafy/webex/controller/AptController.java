package com.ssafy.webex.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.webex.model.dto.HouseDealDto;
import com.ssafy.webex.model.dto.HouseInfoDto;
import com.ssafy.webex.model.dto.ResidenceBuyDto;
import com.ssafy.webex.model.dto.ResidenceRentDto;
import com.ssafy.webex.model.service.HouseDealService;
import com.ssafy.webex.model.service.ResidenceDealService;
import com.ssafy.webex.util.PageNavigation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@Controller
@RequestMapping("/aptdeal")
public class AptController {

	@Autowired
	private HouseDealService houseDealService;

	@Autowired
	private ResidenceDealService residenceDealService;

	@GetMapping("/{dong_name}")
    public ResponseEntity<List<HouseDealDto>> selectHouseinfo(@PathVariable String dong_name) throws Exception {
		System.out.println("aptdeals 실행은 됨");
		List<HouseDealDto> list = houseDealService.searchDong(dong_name);
//		for(HouseDealDto h: list)
//			System.out.println("deals : " + h);
		
        return new ResponseEntity<List<HouseDealDto>>(list, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String goAptSearch(Model model, @RequestParam Map<String, String> map) {
		String spp = map.get("spp");
		map.put("spp", spp != null ? spp : "10");// sizePerPage
		try {
			List<HouseDealDto> list = houseDealService.searchAll(map);
			PageNavigation pageNavigation = houseDealService.makePageNavigation(map);
			model.addAttribute("deals", list);
			model.addAttribute("navigation", pageNavigation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "apt/searchList";
	}
	
	@RequestMapping(value = "/searchAptName", method = RequestMethod.GET)
	public String goAptSearchAptName(Model model, @RequestParam Map<String, String> map, HttpServletRequest request) {
		String aptName = request.getParameter("word");
		System.out.println("아파트 이름 : " + aptName);
		String spp = map.get("spp");
		map.put("aptName", "%" + aptName + "%");
		map.put("spp", spp != null ? spp : "10");// sizePerPage
		try {
			List<HouseDealDto> list = houseDealService.searchAptName(map);
			PageNavigation pageNavigation = houseDealService.makePageNavigation(map);
			model.addAttribute("deals", list);
			model.addAttribute("navigation", pageNavigation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "apt/searchList";
	}
	
//	@RequestMapping(value = "/searchDongName", method = RequestMethod.GET)
//	public String searchDong(Model model, @RequestParam Map<String, String> map, HttpServletRequest request){		
//		String dongName = request.getParameter("word");
//		String spp = map.get("spp");
//		map.put("dongName", "%" + dongName + "%");
//		map.put("spp", spp != null ? spp : "10");
//		try {			
//			List<HouseDealDto> list = houseDealService.searchDong(map);
//			PageNavigation pageNavigation = houseDealService.makePageNavigation(map);
//			model.addAttribute("deals", list);
//			model.addAttribute("navigation", pageNavigation);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "apt/searchList";
//	}
	
	@RequestMapping(value = "/searchRes", method = RequestMethod.GET)
	public String goResSearch(Model model, @RequestParam Map<String, String> map) {
		String spp = map.get("spp");
		map.put("spp", spp != null ? spp : "10");// sizePerPage
		try {
			List<ResidenceBuyDto> list = residenceDealService.searchBuy(map);
			PageNavigation pageNavigation = residenceDealService.makePageNavigation(map);
			
			model.addAttribute("type", "buy");
			model.addAttribute("deals", list);
			model.addAttribute("navigation", pageNavigation);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("에러발생");
		}
		return "apt/searchResList";
	}
	
	@RequestMapping(value = "/searchResName", method = RequestMethod.GET)
	public String goResSearchResName(Model model, @RequestParam Map<String, String> map, HttpServletRequest request) {
		String resName = request.getParameter("word");
		System.out.println("주택 이름 : " + resName);
		String spp = map.get("spp");
		map.put("resName", "%" + resName + "%");
		map.put("spp", spp != null ? spp : "10");// sizePerPage
		try {
			List<ResidenceBuyDto> list = residenceDealService.searchBuyName(map);
			PageNavigation pageNavigation = residenceDealService.makePageNavigation(map);
			model.addAttribute("type", "buy");
			model.addAttribute("deals", list);
			model.addAttribute("navigation", pageNavigation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "apt/searchResList";
	}
	
	@RequestMapping(value = "/searchResDongName", method = RequestMethod.GET)
	public String goResSearchResDongName(Model model, @RequestParam Map<String, String> map, HttpServletRequest request) {
		String dongName = request.getParameter("word");
		System.out.println("동 주택 이름 : " + dongName);
		String spp = map.get("spp");
		map.put("dongName", "%" + dongName + "%");
		map.put("spp", spp != null ? spp : "10");// sizePerPage
		try {
			List<ResidenceBuyDto> list = residenceDealService.searchBuyDongName(map);
			PageNavigation pageNavigation = residenceDealService.makePageNavigation(map);
			model.addAttribute("type", "buy");
			model.addAttribute("deals", list);
			model.addAttribute("navigation", pageNavigation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "apt/searchResList";
	}
	
	
	@RequestMapping(value = "/searchRent", method = RequestMethod.GET)
	public String goRentSearch(Model model, @RequestParam Map<String, String> map) {
		System.out.println("rent 시작");
		String spp = map.get("spp");
		map.put("spp", spp != null ? spp : "10");// sizePerPage
		try {
			List<ResidenceRentDto> list = residenceDealService.searchRent(map);
			PageNavigation pageNavigation = residenceDealService.makePageNavigationRent(map);
			model.addAttribute("type", "rent");
			model.addAttribute("rents", list);
			model.addAttribute("navigation", pageNavigation);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("에러발생");
		}
		System.out.println("rent 끝");
		return "apt/searchResList";
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String show(HttpServletRequest request){
		int aptno = Integer.parseInt(request.getParameter("no"));
		try {
			HouseDealDto houseDeal = houseDealService.show(aptno);
			HttpSession session = request.getSession();
			session.setAttribute("deal", houseDeal);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "상세정보 조회 중 문제가 발생했습니다.");
		}
		return "apt/showApt";
	}
}
