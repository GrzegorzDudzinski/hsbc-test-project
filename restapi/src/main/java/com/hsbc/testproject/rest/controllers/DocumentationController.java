package com.hsbc.testproject.rest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class DocumentationController {
	
	private static final String INDEX_RESOURCE_PATH = "index.html";
	private static final Logger LOG = LoggerFactory.getLogger(DocumentationController.class);

	@RequestMapping(value="/",method = RequestMethod.GET)
    public String homepage(){
		LOG.info("Generating documentation page...");
        return INDEX_RESOURCE_PATH;
    }

}
