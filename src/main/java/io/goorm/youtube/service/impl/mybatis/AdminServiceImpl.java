package io.goorm.youtube.service.impl.mybatis;


import io.goorm.youtube.mapper.AdminMapper;
import io.goorm.youtube.vo.DefaultVO;
import io.goorm.youtube.vo.domain.Admin;
import io.goorm.youtube.service.AdminService;
import io.goorm.youtube.vo.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {


    private final AdminMapper adminMapper;

    @Autowired
    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    public Admin login(Admin admin) {

        return adminMapper.selectByAdminId(admin.getAdminId());
    }

    public List<Admin> findAll(DefaultVO defaultVO) {

        int totalCount = adminMapper.selectCount();
        defaultVO.setTotalCount(totalCount);
        defaultVO.setOffset();

        return adminMapper.selectAll(defaultVO);

    }

    public Admin find(Long adminSeq) {

        return adminMapper.selectById(adminSeq);
    }

    public int save(Admin admin) {

        return adminMapper.insert(admin);
    }

    public int update(Admin admin) {

        return adminMapper.update(admin);
    }

    public int remove(Admin admin) {

        return adminMapper.updateUseYn(admin);
    }

    public int updateUseYn(Admin admin) {

        return adminMapper.updateUseYn(admin);
    }


}
