package io.goorm.youtube.service.impl;


import io.goorm.youtube.mapper.VideoMapper;
import io.goorm.youtube.vo.DefaultVO;
import io.goorm.youtube.vo.domain.Video;
import io.goorm.youtube.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
@org.springframework.stereotype.Service
public class VideoServiceImpl implements VideoService {


    private VideoMapper videoMapper;

    @Autowired
    public VideoServiceImpl(VideoMapper videoMapper) {
        this.videoMapper = videoMapper;
    }

    public List<Video> findIndex(DefaultVO defaultVO) {

        return videoMapper.selectIndex(defaultVO);
    }

    public List<Video> findAll(DefaultVO defaultVO) {

        return videoMapper.selectAll(defaultVO);
    }

    public Video find(Long videoSeq) {

        return videoMapper.selectById(videoSeq);
    }

    public int save(Video video) {

        return videoMapper.insert(video);
    }

    public int update(Video video) {

        return videoMapper.update(video);
    }

    public int updatePublishYn(Video video) {

        return videoMapper.updatePublishYn(video);
    }

    public int updateDeleteYn(Video video) {

        return videoMapper.updateDeleteYn(video);
    }

}
