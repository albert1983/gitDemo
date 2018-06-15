package com.zhuoyue.common.dto;


import com.zhuoyue.common.constant.Constant;

import java.io.Serializable;

/**
 * @author gzd
 * @date 2017/11/6 11:01
 * @desc 分页查询时, controller中的page参数
 */
public class PageVo implements Serializable {
    private static final long serialVersionUID = -5466914801820324238L;

    /**
     * 查询的页数,给一个默认值
     */
    private int pageNo = Constant.DEFAULT_PAGE_NO;

    /**
     * 查询的每页条数,给一个默认值
     */
    private int pageSize = Constant.DEFAULT_PAGE_SIZE;

    public PageVo() {
    }

    public PageVo(int pageNo) {
        setPageNo(pageNo);
    }

    public PageVo(int pageNo, int pageSize) {
        setPageNo(pageNo);
        setPageSize(pageSize);
    }

    public int getPageNo() {
        return pageNo;
    }

    public PageVo setPageNo(int pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public PageVo setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }
}
