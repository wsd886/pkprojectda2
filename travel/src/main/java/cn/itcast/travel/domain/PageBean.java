package cn.itcast.travel.domain;

import java.util.List;

/**
 * 分页对象
 */
public class PageBean {
    //总记录数
    private int totalCount;
    //总页数
    private int totalPage;
    //当前页码
    private int currentPage;
    //每页显示的个数
    private int PageSize;
    //每页显示的数据集合
    private List<Route> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }

    public List<Route> getList() {
        return list;
    }

    public void setList(List<Route> list) {
        this.list = list;
    }

}
