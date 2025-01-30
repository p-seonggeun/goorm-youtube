package io.goorm.youtube.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DefaultVO {

    /** 검색Keyword */
    private String searchKeyword = "";

    /** 현재페이지 */
    private int page = 1;

    /** 페이지사이즈 */
    private int pageSize = 10;

    /** firstIndex */
    private int offset = 0;

    /** 총 항목 수 */
    private int totalCount;

    public int setOffset() {
        return (page - 1) * (pageSize + 1);
    }

    public int getTotalPages() {
        return (int) Math.ceil((double) totalCount / pageSize);
    }

}
