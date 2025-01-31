package io.goorm.youtube.vo.domain;

import io.goorm.youtube.vo.DefaultVO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Member extends DefaultVO {


    private Long memberSeq;

    @NotEmpty(message = "아이디는 필수 입력 사항입니다.")
    @Email(message = "올바른 이메일 형식이어야 합니다.")
    private String memberId;

    @NotEmpty(message = "패스워드는 필수 입력 사항입니다.")
    private String memberPw;

    @NotEmpty(message = "닉네임은 필수 입력 사항입니다.")
    private String memberNick;

    private String memberProfile;

    private String memberInfo;

    private String useYn;
    private String regDate;

    private int videoCnt;

}
