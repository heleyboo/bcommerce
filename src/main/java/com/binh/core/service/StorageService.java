package com.binh.core.service;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface StorageService {
	public String storeFile(String fileName, String fileType, byte[] bytes) throws FileNotFoundException, IOException;
}
