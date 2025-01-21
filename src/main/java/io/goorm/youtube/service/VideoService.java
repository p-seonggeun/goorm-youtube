package io.goorm.youtube.service;


import io.goorm.youtube.mapper.VideoMapper;
import io.goorm.youtube.vo.Video;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
@org.springframework.stereotype.Service
public class VideoService {

    @Autowired
    private VideoMapper videoMapper;

    public List<Video> getVideoForIndex() {

        return videoMapper.selectIndex();
    }

}
