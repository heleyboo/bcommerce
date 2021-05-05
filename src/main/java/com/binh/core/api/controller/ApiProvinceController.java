package com.binh.core.api.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.binh.core.entity.District;
import com.binh.core.entity.Province;
import com.binh.core.entity.Ward;
import com.binh.core.repository.DistrictRepository;
import com.binh.core.repository.ProvinceRepository;
import com.binh.core.repository.WardRepository;
import com.binh.core.service.ProvinceService;
import com.binh.core.service.StorageService;

@RestController
@RequestMapping("/api/v1/provinces")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ApiProvinceController {

	@Autowired
	private ProvinceService provinceService;

	@Autowired
	private ProvinceRepository provinceRepo;

	@Autowired
	private DistrictRepository districtRepo;

	@Autowired
	private WardRepository wardRepo;
	
	@Autowired
	private StorageService storageService;

	@GetMapping("/{id}/districts")
	public List<District> getDistricts(@PathVariable("id") String provinceId) {
		return provinceService.getDistricts(provinceId);
	}

	@GetMapping
	public List<Province> getProvinces() {
		return provinceService.getProvinces();
	}

	@GetMapping("/data")
	public void loadData() throws IOException {
		List<District> districts = districtRepo.findAll();
		List<Ward> listWard = new ArrayList<Ward>();

		for (District district : districts) {
			listWard.addAll(getWards(district.getCode()));
		}

		List<Ward> tmp = listWard.stream().filter(it -> StringUtils.hasText(it.getCode())).collect(Collectors.toList());

		wardRepo.saveAll(tmp);
	}

	public List<Ward> getWards(String districtCode) throws IOException {
		String filePath = String.format("dist/xa-phuong/%s.json", districtCode);
		ClassPathResource res = new ClassPathResource(filePath);
		List<Ward> retList = new ArrayList<Ward>();
		File file = res.getFile();
		// JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader(file.getPath())) {
			// Read JSON file
			Object obj = jsonParser.parse(reader);

			JSONObject wards = (JSONObject) obj;
			Set keys = wards.keySet();

			retList = (List<Ward>) keys.stream().map(code -> {
				JSONObject ward = (JSONObject) wards.get(code);
				return json2Ward(ward);
			}).collect(Collectors.toList());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return retList;
	}

	public Ward json2Ward(JSONObject obj) {
		Ward ward = new Ward();
		ward.setCode((String) obj.get("code"));
		ward.setName((String) obj.get("name"));
		ward.setNameWithType((String) obj.get("name_with_type"));
		ward.setType((String) obj.get("type"));
		ward.setSlug((String) obj.get("slug"));
		ward.setPath((String) obj.get("path"));
		ward.setPathWithType((String) obj.get("path_with_type"));
		String parentCode = (String) obj.get("parent_code");
		District parent = districtRepo.findById(parentCode).orElse(null);
		ward.setDistrict(parent);
		return ward;
	}

}
