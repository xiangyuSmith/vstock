package com.vstock.db.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * Created by xiangyu on 2016/8/1.
 */
public class Ids  implements Serializable {

    @Id
    private String id;
    private String name;
    private int upId;

    public Ids() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUpId() {
        return upId;
    }

    public void setUpId(int upId) {
        this.upId = upId;
    }

    public Ids(String id, String name, int upId) {
        this.id = id;
        this.name = name;
        this.upId = upId;
    }
}
