package com.alexeyzh.controllers;


public class UIFilterRule {
    public String field;
    public String op;
    public String value;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UIFilterRule{");
        sb.append("field='").append(field).append('\'');
        sb.append(", op='").append(op).append('\'');
        sb.append(", value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
