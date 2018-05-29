package com.appchina.collect.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * Created by liujianmeng on 2017/12/19.
 */
public class CategoryDownloadRank {
    private int categoryId;
    private String packageName;
    private int Rank;
//    @JsonIgnore
//    private Integer status;
//    @JsonIgnore
//    private Date createTime;


    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Integer getRank() {
        return Rank;
    }

    public void setRank(Integer rank) {
        Rank = rank;
    }

//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }
//
//    public Integer getStatus() {
//        return status;
//    }
//
//    public void setStatus(Integer status) {
//        this.status = status;
//    }

    @Override
    public String toString() {
        return "{" +
                "categoryId:" + categoryId +
                ", packageName:'" + packageName + '\'' +
                ", Rank:" + Rank +
                '}';
    }
}
