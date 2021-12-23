package com.my.liufeng.ui.model;

import java.util.List;

public interface Relation {
    List<Relation> search(List<Relation> list, String keyword);

    String getIcon();

    String getContent();

    String getTime();

    String getTitle();
}
