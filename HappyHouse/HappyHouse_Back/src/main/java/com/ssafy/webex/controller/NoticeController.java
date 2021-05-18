package com.ssafy.webex.controller;

import java.sql.SQLException;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.webex.model.dto.MemberDto;
import com.ssafy.webex.model.dto.NoticeDto;
import com.ssafy.webex.model.service.NoticeService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@Controller
@RequestMapping("/notice")
public class NoticeController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private NoticeService noticeService;
		
	@GetMapping(value = "/list")
	public ResponseEntity<List<NoticeDto>> goSearchAll(Model model, @RequestParam Map<String, String> map) {
		List<NoticeDto> list = null;
		String pg = map.get("pg");
		String spp = map.get("spp");
		map.put("spp", spp != null ? spp : "10");// sizePerPage
		map.put("pg", pg != null ? pg : "1");// sizePerPage
		try {
			list = noticeService.list(map);
//			PageNavigation pageNavigation = noticeService.makePageNavigation(map);
//			model.addAttribute("list", list);
//			model.addAttribute("navigation", pageNavigation);
			System.out.println("공지 이동");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("notice searchAll 에러발생");
		}
		System.out.println("notice searchAll 올바르게 리턴");
		for(NoticeDto n : list)
			System.out.println(n);
		return new ResponseEntity<List<NoticeDto>>(list, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{no}")
	public ResponseEntity<NoticeDto> show(@PathVariable int no) throws SQLException {
		NoticeDto noticeDto;
		System.out.println("show 호출됨");
		try {
			noticeDto = noticeService.show(no);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("공지사항 디테일 처리중 에러가 발생");
			return null;
		}
		System.out.println("notice show 정상처리");
		System.out.println(noticeDto);
		return new ResponseEntity<NoticeDto>(noticeService.show(no), HttpStatus.OK);
	}
	
	@PostMapping(value = "/create")
	public ResponseEntity<String> doWrite(@RequestBody NoticeDto noticeDto) {
		System.out.println(noticeDto);
		try {
			noticeService.write(noticeDto);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping(value = "/{no}")
	public ResponseEntity<String> modifyInfo(@RequestBody NoticeDto noticeDto){
		System.out.println("업데이트 시작");
		try {
			noticeService.modifyInfo(noticeDto);
			System.out.println(noticeDto);
			System.out.println("업데이트 끝 완료");
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping(value = "/{no}")
	public ResponseEntity<String> delete(@PathVariable int no){
		System.out.println("delete 들어옴" + no);
		try {
			noticeService.delete(no);
			System.out.println("삭제 끝 완료");
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
	
	
	
	
	@RequestMapping(value = "/sitemap", method = RequestMethod.GET)
	public String goSitemap() {
		return "notice/sitemap";
	}

	@RequestMapping(value = "/mvwrite", method = RequestMethod.GET)
	public String goWrite() {
		return "notice/write";
	}

	@RequestMapping(value = "/mvmodify", method = RequestMethod.GET)
	public String moveModify(HttpServletRequest request) {
		int memberNo = Integer.parseInt(request.getParameter("no"));
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		try {
			NoticeDto nDto = new NoticeDto();
			nDto.setId(memberDto.getId());
			nDto = noticeService.getInfo(memberNo);
			session.setAttribute("notice", nDto);
			session.setAttribute("userinfo", memberDto);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("에러발생");
			return "error/error";
		}
		return "/notice/modify";
	}
}
