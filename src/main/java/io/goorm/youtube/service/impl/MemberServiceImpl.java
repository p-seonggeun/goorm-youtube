package io.goorm.youtube.service.impl;

import io.goorm.youtube.mapper.MemberMapper;
import io.goorm.youtube.vo.DefaultVO;
import io.goorm.youtube.vo.domain.Member;
import io.goorm.youtube.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

@Slf4j
@org.springframework.stereotype.Service
public class MemberServiceImpl implements MemberService {



    private MemberMapper memberMapper;

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }


    public List<Member> findAll(DefaultVO defaultVO) {

        int totalCount = memberMapper.selectCount();
        defaultVO.setTotalCount(totalCount);
        defaultVO.setOffset();

        return memberMapper.selectAll(defaultVO);
    }

    public Member login(Member member) {

        return memberMapper.selectByMemberId(member.getMemberId());
    }

    public Member find(Long memberSeq) {

        return memberMapper.selectById(memberSeq);
    }

    public boolean existsById(String memberId) {
        return memberMapper.existsById(memberId);
    }

    public int save(Member member) {

        return memberMapper.insert(member);
    }

    public int update(Member member) {

        return memberMapper.update(member);
    }

    public int updatePublishYn(Member member) {

        return memberMapper.updateUseYn(member);
    }

}
