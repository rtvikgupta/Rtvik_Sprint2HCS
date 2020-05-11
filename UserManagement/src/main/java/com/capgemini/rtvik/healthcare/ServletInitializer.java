package com.capgemini.rtvik.healthcare;

import org.springframework.boot.builder.SpringApplicationBuilder;

public class ServletInitializer {

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(UserManagementApplication.class);
	}
}
