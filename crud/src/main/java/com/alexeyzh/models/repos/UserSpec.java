package com.alexeyzh.models.repos;

import com.alexeyzh.controllers.UIFilterRule;
import com.alexeyzh.controllers.UserController;
import com.alexeyzh.models.entities.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Date;


public class UserSpec {

    public static Specification<User> find(final UIFilterRule[] filter) {
        return new Specification<User>() {
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                Predicate where = null;
                for (final UIFilterRule rule : filter) {

                    Predicate predicate = null;
                    Object value = getFieldValue(rule);
                    if (value == null) {
                        continue;
                    }

                    Class clazz = getFieldClass(rule);
                    Expression expression = root.get(rule.field).as(clazz);

                    switch (rule.op) {
                        case "contains":
                            predicate = builder.like(expression, "%" + value.toString() + "%");
                            break;
                        case "less":
                            predicate = builder.lessThan(expression, (Comparable)value);
                            break;
                        case "greater":
                            predicate = builder.greaterThan(expression, (Comparable)value);
                            break;
                        case "equal":
                            predicate = builder.equal(expression, value);
                            break;
                    }
                    if (predicate == null) {
                        continue;
                    }
                    if (where == null) {
                        where = predicate;
                    } else {
                        where = builder.and(where, predicate);
                    }
                }
                return where;
            }
        };
    }

    private static Class getFieldClass(UIFilterRule rule) {
        switch (rule.field) {
            case "id":
                return Long.class;
            case "name":
                return String.class;
            case "age":
                return Integer.class;
            case "isAdmin":
                return Integer.class;
            case "createDate":
                return Date.class;
            default:
                return Object.class;
        }
    }

    private static Object getFieldValue(UIFilterRule rule) {
        try {
            switch (rule.field) {
                case "id":
                    return Long.valueOf(rule.value);
                case "name":
                    return rule.value;
                case "age":
                    return Integer.valueOf(rule.value);
                case "isAdmin":
                    return Boolean.valueOf(rule.value) ? 1 : 0;
                case "createDate":
                    return UserController.DATE_FORMAT.parse(rule.value);
                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}