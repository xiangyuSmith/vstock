package com.vstock.db.dao;

import com.vstock.db.entity.Menu;

import java.util.List;

/**
 * Created by xiangyu on 2016/6/23.
 */
public interface IMenu {

    List<Menu> findMenuList();

}
