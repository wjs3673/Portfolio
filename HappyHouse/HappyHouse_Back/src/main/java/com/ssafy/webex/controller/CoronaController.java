package com.ssafy.webex.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.ssafy.webex.model.dto.BookmarkDto;
import com.ssafy.webex.model.dto.CoronaClinicDto;
import com.ssafy.webex.model.dto.CoronaHospitalDto;
import com.ssafy.webex.model.service.CoronaClinicService;
import com.ssafy.webex.model.service.CoronaClinicServiceImpl;
import com.ssafy.webex.model.service.CoronaHospitalService;
import com.ssafy.webex.util.PageNavigation;

/**
 * Servlet implementation class CoronaController
 */
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@Controller
@RequestMapping("/corona")
public class CoronaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	@Autowired
	private CoronaClinicService coronaClinicService;
	
	
	@RequestMapping(value = "/searchAll", method = RequestMethod.GET)
	public String goSearchAll(Model model, @RequestParam Map<String, String> map) {
		String spp = map.get("spp");
		map.put("spp", spp != null ? spp : "10");//sizePerPage
		try {			
			List<CoronaClinicDto> list = coronaClinicService.searchAll(map);
			PageNavigation pageNavigation = coronaClinicService.makePageNavigation(map);
			for(CoronaClinicDto c: list) {
				System.out.println(c);
			}
			model.addAttribute("coronas", list);
			model.addAttribute("navigation", pageNavigation);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return "corona/searchList";
	}
	
	public void init() {
		coronaClinicService = new CoronaClinicServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

//	private void searchAll(HttpServletRequest request, HttpServletResponse response) {
//		String path = "/apt/search.jsp";
//		String pg = request.getParameter("pg");
//		int currentPage = pg ==null || pg.contentEquals("") ? 1 : Integer.parseInt(request.getParameter("pg"));
//		System.out.println(">>>>>>>>"+currentPage);
//		String spp = request.getParameter("spp"); // 한페이지당 글 갯수
//		System.out.println(">>>>>>>>>>>>>>"+spp);
//		int sizePerPage = spp == null ? 3 : Integer.parseInt(spp);//sizePerPage
//		
//		try {			
//			List<CoronaClinicDto> list = coronaClinicService.searchAll(currentPage, sizePerPage);
//			PageNavigation pageNavigation = coronaClinicService.makePageNavigation("", "", currentPage, sizePerPage);
//			
//			for(CoronaClinicDto c: list) {
//				System.out.println(c);
//			}
//			
//			request.setAttribute("coronas", list);
//			request.setAttribute("navigation", pageNavigation);
//			path = "/corona/searchList.jsp";
//			request.getRequestDispatcher(path).forward(request, response);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	
}