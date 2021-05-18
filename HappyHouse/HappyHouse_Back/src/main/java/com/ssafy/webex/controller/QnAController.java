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
import com.ssafy.webex.model.dto.QnADto;
import com.ssafy.webex.model.service.QnAService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@Controller
@RequestMapping("/qna")
public class QnAController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private QnAService qnaService;
		
	@GetMapping(value = "/list")
	public ResponseEntity<List<QnADto>> goSearchAll(Model model, @RequestParam Map<String, String> map) {
		List<QnADto> list = null;
		String pg = map.get("pg");
		String spp = map.get("spp");
		map.put("spp", spp != null ? spp : "10");// sizePerPage
		map.put("pg", pg != null ? pg : "1");// sizePerPage
		try {
			list = qnaService.list(map);
			System.out.println("QnA 이동");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("qna searchAll 에러발생");
		}
		System.out.println("qna searchAll 올바르게 리턴");
		for(QnADto n : list)
			System.out.println(n);
		return new ResponseEntity<List<QnADto>>(list, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{no}")
	public ResponseEntity<QnADto> show(@PathVariable int no) throws SQLException {
		QnADto qnaDto;
		System.out.println("show 호출됨");
		try {
			qnaDto = qnaService.show(no);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("QnA 디테일 처리중 에러가 발생");
			return null;
		}
		System.out.println("qna show 정상처리");
		System.out.println(qnaDto);
		return new ResponseEntity<QnADto>(qnaService.show(no), HttpStatus.OK);
	}
	
	@PostMapping(value = "/create")
	public ResponseEntity<String> doWrite(@RequestBody QnADto qnaDto) {
		System.out.println(qnaDto);
		try {
			qnaService.write(qnaDto);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping(value = "/{no}")
	public ResponseEntity<String> modifyInfo(@RequestBody QnADto qnaDto){
		System.out.println("업데이트 시작");
		try {
			qnaService.modifyInfo(qnaDto);
			System.out.println(qnaDto);
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
			qnaService.delete(no);
			System.out.println("삭제 끝 완료");
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(value = "/sitemap", method = RequestMethod.GET)
	public String goSitemap() {
		return "qna/sitemap";
	}

	@RequestMapping(value = "/mvwrite", method = RequestMethod.GET)
	public String goWrite() {
		return "qna/write";
	}

	@RequestMapping(value = "/mvmodify", method = RequestMethod.GET)
	public String moveModify(HttpServletRequest request) {
		int memberNo = Integer.parseInt(request.getParameter("no"));
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		try {
			QnADto nDto = new QnADto();
			nDto.setId(memberDto.getId());
			nDto = qnaService.getInfo(memberNo);
			session.setAttribute("qna", nDto);
			session.setAttribute("userinfo", memberDto);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("에러발생");
			return "error/error";
		}
		return "/qna/modify";
	}
}
