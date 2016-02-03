package com.alexeyzh.controllers;

import com.alexeyzh.config.UIFilterEditor;
import com.alexeyzh.models.entities.User;
import com.alexeyzh.models.repos.DbUser;
import com.alexeyzh.models.repos.UserSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



@Controller
public class UserController {
    static public final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
    static {
        DATE_FORMAT.setLenient(false);
    }

    @Autowired
    DbUser dbUser;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(DATE_FORMAT, false));
        binder.registerCustomEditor(Boolean.class, new CustomBooleanEditor("да", "нет", false));
        binder.registerCustomEditor(UIFilterRule[].class, new UIFilterEditor());
    }

    @RequestMapping(value = "user_create", method = RequestMethod.POST)
    public
    @ResponseBody
    User create(
            @RequestParam("name") String name,
            @RequestParam("age") int age,
            @RequestParam("isAdmin") Boolean isAdmin,
            @RequestParam("createDate") Date createDate) {
        User user = dbUser.save(new User(name, age, isAdmin, createDate));
        return user;
    }


    @RequestMapping(value = "user_read", method = RequestMethod.GET)
    public
    @ResponseBody
    UIUserPage read(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "rows") final int rows,
            @RequestParam(value = "sort", required = false) final String sort,
            @RequestParam(value = "order", required = false) final String order,
            @RequestParam(value = "filterRules", required = false) final UIFilterRule[] filters) {

        PageRequest pageRequest = new PageRequest(page - 1, rows,
                new Sort("DESC".equalsIgnoreCase(order) ? Sort.Direction.DESC : Sort.Direction.ASC,
                        sort == null ? "id" : sort));

        Page<User> usersPage;
        if (filters != null && filters.length > 0) {
            usersPage = dbUser.findAll(UserSpec.find(filters), pageRequest);
        } else {
            usersPage = dbUser.findAll(pageRequest);
        }

        List<User> users = usersPage.getContent();
        if (users.isEmpty()) {
            return new UIUserPage(null, 0);
        } else {
            return new UIUserPage(users, usersPage.getTotalElements());
        }
    }

    @RequestMapping(value = "user_update", method = RequestMethod.POST)
    public
    @ResponseBody
    User update(
            @RequestParam("id") long id,
            @RequestParam("name") String name,
            @RequestParam("age") int age,
            @RequestParam("isAdmin") Boolean isAdmin,
            @RequestParam("createDate") Date createDate) {

        User user = dbUser.findOne(id);
        if (user != null) {
            user.setName(name);
            user.setAge(age);
            user.setIsAdmin(isAdmin);
            user.setCreateDate(createDate);
            return dbUser.save(user);
        } else {
            return null;
        }
    }

    @RequestMapping(value = "user_delete", method = RequestMethod.POST)
    public
    @ResponseBody
    boolean deleteTranslit(@RequestParam("id") final long id) {
        dbUser.delete(id);
        return true;
    }
}
