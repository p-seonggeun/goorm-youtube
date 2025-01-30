package io.goorm.youtube.vo.domain;

import io.goorm.youtube.vo.DefaultVO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Member extends DefaultVO {

    private Long memberSeq;
    private String memberId;
    private String memberPw;
    private String memberNick;

    private String memberProfile;

    private String memberInfo;

    private String useYn;
    private String regDate;

    private int videoCnt;

}
