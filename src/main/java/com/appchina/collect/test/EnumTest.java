package com.appchina.collect.test;

public enum EnumTest {
    title(1, "标题"),
    description(2, "描述"),
    bookname(5, "书名");

    private Integer id;
    private String name;
    EnumTest(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
