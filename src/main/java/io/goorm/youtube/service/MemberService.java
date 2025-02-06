package io.goorm.youtube.service;

import io.goorm.youtube.vo.DefaultVO;
import io.goorm.youtube.vo.domain.Admin;
import io.goorm.youtube.vo.domain.Member;

import java.io.IOException;
import java.util.List;


public interface MemberService {

    public List<Member> findAll(DefaultVO defaultVO);

    public Member login(Member member);

    public Member find(Long seq);

    public int resetPw(Member member);

    public boolean existsById(String seq);

    public int save(Member member);

    public int update(Member member);

    public int updateUseYn(Member member);

}
