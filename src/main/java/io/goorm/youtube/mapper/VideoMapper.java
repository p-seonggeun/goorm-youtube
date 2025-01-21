package io.goorm.youtube.mapper;

import io.goorm.youtube.vo.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VideoMapper {

    List<Video> selectIndex();

    Video selectById(@Param("videoSeq") Long seq);

    int insert(@Param("video") Video video);

    int update(@Param("video") Video video);

    int updatePublishYn(@Param("video") Video video);

    int updateDeleteYn(@Param("video") Video video);

    int delete(@Param("videoSeq") Long seq);

}
