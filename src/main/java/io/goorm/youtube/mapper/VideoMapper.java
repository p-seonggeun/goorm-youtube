package io.goorm.youtube.mapper;

import io.goorm.youtube.vo.DefaultVO;
import io.goorm.youtube.vo.domain.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VideoMapper {

    int selectCount();


    List<Video> selectIndex();

    List<Video> selectAll(@Param("defaultVO") DefaultVO defaultVO);

    Video selectById(@Param("videoSeq") Long seq);

    int insert(@Param("video") Video video);

    int update(@Param("video") Video video);

    int updatePublishYn(@Param("video") Video video);

    int updateDeleteYn(@Param("video") Video video);

    int delete(@Param("videoSeq") Long seq);

}
