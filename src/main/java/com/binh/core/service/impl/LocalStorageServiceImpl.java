package com.binh.core.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.binh.core.service.StorageService;

@Service
@Primary
public class LocalStorageServiceImpl implements StorageService {

	@Override
	public String storeFile(String fileName, String fileType, byte[] bytes) throws FileNotFoundException, IOException {
		System.out.println(fileType);
		String imagePath = "";
		try {
			String ext = "png";
			ClassPathResource res = new ClassPathResource("public/upload");
			ClassLoader classLoader = getClass().getClassLoader();
			String filePath = String.format("%s/%s.%s", classLoader.getResource("public/upload").getFile(), fileName, ext);
			File file = new File(filePath);
			file.createNewFile();
			@SuppressWarnings("resource")
			OutputStream stream = new FileOutputStream(file.getAbsolutePath());
			stream.write(bytes);
			imagePath = String.format("upload/%s.%s", fileName, ext);
		} catch (Exception e) {
			throw e;
		}
		return imagePath;
	}

}
