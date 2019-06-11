package com.rainng.rainngojtoy.models.dto;

public class PageInfoDTO {
    private Integer itemCount;
    private Integer pageSize;

    public PageInfoDTO() {

    }

    public PageInfoDTO(Integer itemCount, Integer pageSize) {
        this.itemCount = itemCount;
        this.pageSize = pageSize;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
