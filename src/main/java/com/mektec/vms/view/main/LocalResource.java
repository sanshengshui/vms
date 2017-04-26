package com.mektec.vms.view.main;

import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;

import java.io.File;

public class LocalResource {
	public static String ExactFilePath(String filePath) {
		String basepath = VaadinService.getCurrent()
				.getBaseDirectory().getAbsolutePath();
		return basepath + "/WEB-INF/" + filePath;
	}
	
	public static FileResource get(String filePath) {
		return new FileResource(new File(LocalResource.ExactFilePath(filePath)));
	}
}
