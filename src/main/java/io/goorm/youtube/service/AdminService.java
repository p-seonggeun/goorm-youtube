package io.goorm.youtube.service;


import io.goorm.youtube.vo.DefaultVO;
import io.goorm.youtube.vo.domain.Admin;
import io.goorm.youtube.vo.domain.Member;

import java.util.List;


public interface AdminService {

    public List<Admin> findAll(DefaultVO defaultVO);

    public Admin find(Long seq);

    public Admin login(Admin admin);

    public int save(Admin admin);

    public int update(Admin admin);

    public int remove(Admin admin);

    public int updateUseYn(Admin admin);

}
