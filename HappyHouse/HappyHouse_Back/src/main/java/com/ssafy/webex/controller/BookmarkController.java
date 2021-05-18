package com.ssafy.webex.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.webex.model.dto.BookmarkDto;
import com.ssafy.webex.model.dto.HouseDealDto;
import com.ssafy.webex.model.dto.HouseInfoDto;
import com.ssafy.webex.model.dto.ResidenceBuyDto;
import com.ssafy.webex.model.dto.ResidenceRentDto;
import com.ssafy.webex.model.dto.SidoGugunCodeDto;
import com.ssafy.webex.model.service.BookmarkService;

//http://localhost:9999/vue/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/bookmark")
public class BookmarkController {

    private static final Logger logger = LoggerFactory.getLogger(BookmarkController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private BookmarkService bookmarkService;
    

    @GetMapping("/get/{user_id}/{dongName}")
    public ResponseEntity<List<BookmarkDto>> selectBookmarks(@PathVariable String user_id, @PathVariable String dongName) throws Exception {
    	List<BookmarkDto> list = bookmarkService.select(new BookmarkDto(0,user_id,dongName,"",""));
    	for(BookmarkDto b : list) {
    		System.out.println(b);
    	}
        return new ResponseEntity<List<BookmarkDto>>(list, HttpStatus.OK);
    }
    
    @GetMapping("/insert/{user_id}/{dongName}/{buildingno}/{buildingName}")
    public ResponseEntity<Integer> insertBookmarks(@PathVariable String user_id, @PathVariable String dongName,@PathVariable String buildingno, @PathVariable String buildingName) throws Exception {
        
    	try {
    		int no = bookmarkService.insert(new BookmarkDto(0,user_id,dongName,buildingno,buildingName));
    		
			return new ResponseEntity<Integer>(no, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Integer>(-1, HttpStatus.NO_CONTENT);
		}
    	
    }
    
    @GetMapping("/delete/{no}")
    public ResponseEntity<String> deleteBookmarks(@PathVariable int no) throws Exception {
        
    	try {
    		bookmarkService.delete(no);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
    	
    }
    
}