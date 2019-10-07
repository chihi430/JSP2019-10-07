package com.joker.member.db;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MemberDAO {
	private SqlSession session;
	
	// 싱글톤
	private static MemberDAO instance = new MemberDAO();
	private MemberDAO(){
	}
	public static MemberDAO getInstance(){
		return instance;
	}
	
	// myBatis
	private SqlSession getSession() {
		Reader reader=null;
		try {
			reader = Resources.getResourceAsReader("./SqlMapConfig.xml");
			SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(reader);
			session = sf.openSession(true);
		} catch (IOException e) {
			System.out.println("getSession 에러 : " + e);
			e.printStackTrace();
		}
		return session;
	}
	
	// 중복 아이디 체크
	public int idCheck(String id){
		int result = 0;
		try {
			session = getSession();
			result = (Integer) session.selectOne("member.member_check", id);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("idCheck 에러 : " + e);
		}
		return result;
	}
	
	// 회원 가입
	public void memberJoin(MemberDTO member){
		try {
			session = getSession();
			session.insert("member.member_insert", member);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("memberJoin 에러 : " + e);
		}
	}
	
	// 비밀번호 검사
	public String pwCheck(String id) {
		String passwd = "";
		try {
			session = getSession();
			passwd = (String) session.selectOne("member.passwd_check", id);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("pwCheck 에러 : " + e);
		}
		return passwd;
	}

	// 특정 회원 정보 검색(로그인)
	public MemberDTO getMember(String id) {
		MemberDTO member = null;
		try {
			session = getSession();
			member = (MemberDTO) session.selectOne("member.get_member", id);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getMember 에러 : " + e);
		}
		return member;
	}
	
	// 정보 수정
	public int updateMember(MemberDTO member){
		int result = 0;
		try {
			session = getSession();
			result = (Integer) session.update("member.update_member", member);
			System.out.println("result : " + result);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("updateMember 에러 : " + e);
		}
		return result;
	}
	
	// 비밀번호 수정
	public int pwChange(MemberDTO member) {
		int result = 0;
		try {
			session = getSession();
			result = session.update("member.passwd_change", member);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("pwChange 에러 : " + e);
		}
		return result;
	}
	
	// 회원탈퇴
	public int memDelete(String id) {
		int result = 0;
		try {
			session = getSession();
			result = session.delete("member.member_delete", id);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("memDelete 에러 : " + e);
		}
		return result;
	}
}