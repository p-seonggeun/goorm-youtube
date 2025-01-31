package io.goorm.youtube.vo.domain;

import io.goorm.youtube.vo.DefaultVO;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Video extends DefaultVO {

    private Long videoSeq;

    private String video;
    private String videoThumnail;

    @NotBlank(message = "title is mandatory")
    private String title;
    private String content;

    private String channelName;

    private int publishYn;
    private String deleteYn;

    private String memberSeq;
    private String regDate;


}
