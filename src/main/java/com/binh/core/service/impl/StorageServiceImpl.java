package com.binh.core.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.binh.core.service.StorageService;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Bucket.BlobTargetOption;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.Storage.BucketField;
import com.google.cloud.storage.Storage.BucketGetOption;
import com.google.cloud.storage.StorageOptions;

@Service
public class StorageServiceImpl implements StorageService {

	public static String BUCKET = "motel";
	public static String PROJECT = "weka-210614";

	@Override
	public String storeFile(String fileName, String fileType, byte[] bytes) throws FileNotFoundException, IOException {
		ClassPathResource res = new ClassPathResource("key.json");
		String key = res.getFile().getAbsolutePath();
		Credentials credentials = GoogleCredentials.fromStream(new FileInputStream(key));
		Storage storage = StorageOptions.newBuilder().setCredentials(credentials).setProjectId(PROJECT).build()
				.getService();

		Bucket motel = storage.get(BUCKET, BucketGetOption.fields(BucketField.METAGENERATION));

		motel.create(fileName, bytes, fileType, BlobTargetOption.generationMatch(0));

		return String.format("https://storage.googleapis.com/%s/%s", BUCKET, fileName);
	}

}
