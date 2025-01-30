package io.goorm.youtube.vo.domain;

import io.goorm.youtube.vo.DefaultVO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Admin extends DefaultVO {

    private Long adminSeq;
    private String adminId;
    private String adminPw;
    private String adminName;
    private String useYn;

    private String regSeq;
    private String regName;
    private String regDate;
}
