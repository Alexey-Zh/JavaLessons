package com.alexeyzh.config;

import com.alexeyzh.controllers.UIFilterRule;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyEditorSupport;
import java.io.IOException;


public class UIFilterEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        ObjectMapper mapper = new ObjectMapper();
        UIFilterRule[] value = null;
        try {
            value = mapper.readValue(text, UIFilterRule[].class);
        } catch (IOException e) {
            // handle error
        }
        setValue(value);
    }
}