package io.goorm.youtube.service;


import io.goorm.youtube.vo.DefaultVO;
import io.goorm.youtube.vo.domain.Video;


import java.util.List;


public interface VideoService {

    public List<Video> findIndex(DefaultVO defaultVO);

    public List<Video> findAll(DefaultVO defaultVO);

    public Video find(Long videoSeq);

    public int save(Video video);

    public int update(Video video);

    public int updatePublishYn(Video video);

    public int updateDeleteYn(Video video);

}
