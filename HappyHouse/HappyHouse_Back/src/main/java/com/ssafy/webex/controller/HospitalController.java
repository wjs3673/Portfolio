package com.ssafy.webex.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.ssafy.webex.model.dto.CoronaHospitalDto;
import com.ssafy.webex.model.service.CoronaHospitalService;
import com.ssafy.webex.util.PageNavigation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@Controller
@RequestMapping("/hospital")
public class HospitalController{
	
	@Autowired
	private CoronaHospitalService coronaHospitalService;
	
	@RequestMapping(value = "/searchAll", method = RequestMethod.GET)
	public String goSearchAll(Model model, @RequestParam Map<String, String> map) {
		String spp = map.get("spp");
		map.put("spp", spp != null ? spp : "10");//sizePerPage
		try {			
			List<CoronaHospitalDto> list = coronaHospitalService.searchAll(map);
			PageNavigation pageNavigation = coronaHospitalService.makePageNavigation(map);
			
			for(CoronaHospitalDto c: list) {
				System.out.println(c);
			}
			model.addAttribute("hospitals", list);
			model.addAttribute("navigation", pageNavigation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "hospital/searchList";
	}
	
	@GetMapping(value = "/selectAll/{pageNum}")
	public ResponseEntity<List<CoronaHospitalDto>> selectAll(@PathVariable int pageNum) {
			List<CoronaHospitalDto> list = coronaHospitalService.selectAll(pageNum);
		return new ResponseEntity<List<CoronaHospitalDto>>(list, HttpStatus.OK);
	}
	
	@GetMapping(value = "/count")
	public ResponseEntity<Integer> getCount() {
		return new ResponseEntity<Integer>(coronaHospitalService.getCount(), HttpStatus.OK);
	}
	
}