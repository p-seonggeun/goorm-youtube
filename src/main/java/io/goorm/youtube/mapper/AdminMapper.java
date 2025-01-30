package io.goorm.youtube.mapper;

import io.goorm.youtube.vo.DefaultVO;
import io.goorm.youtube.vo.domain.Admin;
import io.goorm.youtube.vo.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {

    int selectCount();

    List<Admin> selectAll(@Param("defaultVO") DefaultVO defaultVO);

    Admin selectById(@Param("adminSeq") Long adminSeq);

    Admin selectByAdminId(@Param("adminId") String adminId);

    int insert(@Param("admin") Admin admin);

    int update(@Param("admin") Admin admin);

    int updateUseYn(@Param("admin") Admin admin);

    int delete(@Param("adminSeq") Long adminSeq);

}
