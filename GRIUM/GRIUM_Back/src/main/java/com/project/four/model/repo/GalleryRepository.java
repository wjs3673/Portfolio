package com.project.four.model.repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.four.model.dto.AlertDto;
import com.project.four.model.dto.GaddressDto;
import com.project.four.model.dto.GalleryDto;
import com.project.four.model.dto.GoneDto;
import com.project.four.model.dto.RipDto;
import com.project.four.util.Pagination;

@Repository
public class GalleryRepository {
	
	@Autowired
	private SqlSession session;
	
	public void upload(GalleryDto gallery) {
		session.insert("ssafy.gallery.insert", gallery);
	}
	
	public void registfile(GalleryDto gallery) {
		session.insert("ssafy.gallery.fileregist", gallery);
	}
	
	public String find_id(String email) {
		String re = session.selectOne("ssafy.gallery.findid", email);
		System.out.println("reposi : re : >> "+ re);
		return re;
	}
	
	public GoneDto find_gone(String gone_id, String user_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gone_id", gone_id);
		map.put("user_id", user_id);
		return session.selectOne("ssafy.gallery.findgone", map);
	}
	
	public int get_total(int isOwner, String gone_id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isOwner", isOwner);
        map.put("gone_id", gone_id);
        return session.selectOne("ssafy.gallery.getCnt", map);
    }
	
	public List<GalleryDto> get_allList(Pagination pagination){
		List<GalleryDto> list = null;
		if(session.selectList("ssafy.gallery.allList", pagination) != null) list = session.selectList("ssafy.gallery.allList", pagination);
		return list;
	}
	
	public List<GaddressDto> list_id(int photo_id){
		List<GaddressDto> list = session.selectList("ssafy.gallery.IdList", photo_id);
		return list;
	}
	
	public int delete(int photo_id) {
		return session.update("ssafy.gallery.delupdate", photo_id);
	}
	
	public GalleryDto list_one(int photo_id) {
		return session.selectOne("ssafy.gallery.ListOne", photo_id);
	}
	
	//RIP : 애도합니다!
	
	public int checkrip(RipDto rip) throws Exception{
		int result =2;
		if(session.selectOne("ssafy.rip.photorip", rip) != null) result = session.selectOne("ssafy.rip.photorip", rip);
		return result;
	}
	
	public int cancle(RipDto rip) throws Exception{
		int result = session.update("ssafy.rip.photocancle", rip);
		result = session.update("ssafy.rip.photosetCount", rip);
		return result;
	}
	
	public int pressrip(RipDto rip) throws Exception{
		int result = session.insert("ssafy.rip.photopressrip", rip);
		result = session.update("ssafy.rip.photoplusCount", rip);
		return result;
	}
	
	public int updaterip(RipDto rip) throws Exception{
		int result = session.update("ssafy.rip.photoupdaterip", rip);
		result = session.update("ssafy.rip.photoplusCount", rip);
		return result;
	}
	
	//너이놈! 신고합니다
	public int checkalert(AlertDto alert) throws Exception{
		int result = 2;
		if(session.selectOne("ssafy.alert.PCheckalert", alert) != null) result = session.selectOne("ssafy.alert.PCheckalert", alert);
		System.out.println(result);
		System.out.println("alert.photo.id"+alert.getPhoto_id());
		return result;
	}
	
	public int pressalert(AlertDto alert) throws Exception{
		int result = session.insert("ssafy.alert.PPressalert", alert);
		return result;
	}

	public int upalert(AlertDto alert) throws Exception{
		int result = session.update("ssafy.alert.PUpalert", alert);
		return result;
	}

	public int canalert(AlertDto alert) throws Exception{
		int result = session.update("ssafy.alert.PCanalert", alert);
		return result;
	}
	
	public void alterzero(String gone_id, int photo_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gone_id", gone_id);
		map.put("photo_id", photo_id);
		session.update("ssafy.alert.AlterZero", map);
	}
	
	public void alterone(String gone_id, int photo_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gone_id", gone_id);
		map.put("photo_id", photo_id);
		session.update("ssafy.alert.AlterOne", map);
	}
	
	public void ripone(String user_id, int photo_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("photo_id", photo_id);
		session.update("ssafy.rip.RipOne", map);
	}
	
	public void ripzero(String user_id, int photo_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("photo_id", photo_id);
		session.update("ssafy.rip.RipZero", map);
	}
	public int areuAuth(String gone_id, String user_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gone_id", gone_id);
		map.put("user_id", user_id);
		int result =0;
		System.out.println("gone_id"+gone_id);
		System.out.println("user_id"+user_id);
		if(session.selectOne("ssafy.gallery.AreUAuth", map) != null) result = session.selectOne("ssafy.gallery.AreUAuth", map);
		System.out.println("여기는 레포지토리 : "+ result);
		return result;
	}
	
	
	
}