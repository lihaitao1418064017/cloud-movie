package org.lht.boot.web.api.param;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author LiHaitao
 * @description PagerResult: 分页结果
 * @date 2020/1/8 15:43
 **/
@Data
public class PagerResult<E> extends Page<E> {

    private Long totalPages;

    public PagerResult(int current, int size) {
        super(current, size);
    }

    public PagerResult() {
        super();
    }

    public void totalPages() {
        if (getSize() > 0) {
            this.totalPages = this.getTotal() % this.getSize() == 0 ? this.getTotal() / this.getSize() : this.getTotal() / this.getSize() + 1;
        }
    }

    public void setPage(QueryParam queryParam) {
        this.setCurrent(queryParam.getPageNo());
        this.setSize(queryParam.getPageSize());
    }


    public <VO> PagerResult<VO> convertTo(Function<E, VO> converter) {
        PagerResult<VO> newResult = new PagerResult<VO>();
        newResult.setTotal(this.getTotal());
        newResult.setTotalPages(this.totalPages);
        newResult.setCurrent(this.getCurrent());
        newResult.setSize(this.getSize());
        List<VO> collect = this.getRecords().stream().map(converter::apply).collect(Collectors.toList());
        newResult.setRecords(collect);
        return newResult;
    }


}
