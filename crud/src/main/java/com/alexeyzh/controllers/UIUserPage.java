package com.alexeyzh.controllers;

import com.alexeyzh.models.entities.User;

import java.util.ArrayList;
import java.util.List;


public class UIUserPage {
    public long total;
    public List<User> rows;

    public UIUserPage(final List<User> users, final long total) {
        this.total = total;
        if (users != null) {
            this.rows = new ArrayList<>(users);
        } else {
            this.rows = new ArrayList<>();
        }
    }
}
