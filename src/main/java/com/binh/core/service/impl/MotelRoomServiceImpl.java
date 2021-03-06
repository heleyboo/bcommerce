package com.binh.core.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.binh.core.dto.request.MotelRoomDTO;
import com.binh.core.entity.District;
import com.binh.core.entity.MotelRoom;
import com.binh.core.entity.Province;
import com.binh.core.entity.RoomImage;
import com.binh.core.entity.Ward;
import com.binh.core.enums.RoomDirection;
import com.binh.core.repository.MotelRoomRepository;
import com.binh.core.repository.RoomImageRepository;
import com.binh.core.service.CategoryService;
import com.binh.core.service.DistrictService;
import com.binh.core.service.KeyCloakService;
import com.binh.core.service.MotelRoomService;
import com.binh.core.service.ProvinceService;
import com.binh.core.service.StorageService;
import com.binh.core.service.WardService;
import com.binh.core.specifications.Filter;
import com.binh.core.specifications.MotelRoomSpecification;
import com.binh.core.specifications.RoomSpecification;
import com.binh.core.specifications.SearchCriteria;
import com.binh.core.util.CustomStringUtils;

import javassist.NotFoundException;

@Service
public class MotelRoomServiceImpl implements MotelRoomService {
	@Autowired
	private MotelRoomRepository repo;

	@Autowired
	private RoomImageRepository roomImageRepo;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProvinceService provinceService;

	@Autowired
	private DistrictService districtService;

	@Autowired
	private KeyCloakService keyCloakService;

	@Autowired
	private StorageService storageService;

	@Autowired
	private WardService wardService;

	public Page<MotelRoom> getAll(Integer pageNum, Integer pageSize) {

		Pageable pageAble = PageRequest.of(pageNum, pageSize);

		return repo.findAll(pageAble);
	}

	@Override
	public MotelRoom save(MotelRoomDTO room, KeycloakAuthenticationToken authentication)
			throws NotFoundException, FileNotFoundException, IOException {
		MotelRoom motelRoom = new MotelRoom();
		motelRoom.setTitle(room.getTitle());
		motelRoom.setDescription(room.getDescription());
		motelRoom.setPrice(room.getPrice());
		motelRoom.setArea(room.getArea());
		motelRoom.setDepositAmount(room.getDepositAmount());
		motelRoom.setBalconyDirection(room.getBalconyDirection());
		motelRoom.setDoorDirection(room.getDoorDirection());
		motelRoom.setNumOfBedrooms(room.getNumOfBedrooms());
		motelRoom.setNumOfToilets(room.getNumOfToilets());

		String wardCode = room.getWard();
		Ward ward = wardService.getWardByCode(wardCode).orElseThrow(() -> new NotFoundException("Ward not found"));

		District district = ward.getDistrict();
		
		Province province = district.getProvince();
		
		String fullAddress = String.format(
				"%s, %s, %s, %s",
				room.getAddress(),
				ward.getNameWithType(),
				district.getNameWithType(),
				province.getNameWithType());
		
		motelRoom.setFullAddress(fullAddress);
		motelRoom.setProvince(province.getNameWithType());
		
		motelRoom.setWardCode(ward.getCode());
		motelRoom.setDistrictCode(district.getCode());
		motelRoom.setProvinceCode(province.getCode());
		motelRoom.setAddress(room.getAddress());
		motelRoom.setCategory(categoryService.getCategoryByCode(room.getCategory()));
		motelRoom.setAddress(room.getAddress());
		motelRoom.setPhoneNumber(room.getPhoneNumber());
		motelRoom.setSlug(room.getSlug());
		motelRoom.setUserName(keyCloakService.getAuthUsername(authentication));

		List<String> images = room.getImages();

		MotelRoom saved = repo.save(motelRoom);

		saveRoomImages(saved, images);

		return saved;
	}

	private void saveRoomImages(MotelRoom motelRoom, List<String> images) throws FileNotFoundException, IOException {
		List<RoomImage> roomImages = new ArrayList<RoomImage>();
		for (int i = 0; i < images.size(); i++) {
			String base64 = images.get(i);
			String header = CustomStringUtils.findBase64ImageHeader(base64);
			String imageType = CustomStringUtils.findBase64ImageType(base64);

			String base64Image = base64.replace(header, "");
			byte[] imageByte = Base64.getMimeDecoder().decode(base64Image);
			UUID uuid = UUID.randomUUID();
			String uuidAsString = uuid.toString();
			String fileName = String.format("%s_%s", motelRoom.getSlug(), uuidAsString);
			String url = storageService.storeFile(fileName, imageType, imageByte);
			RoomImage roomImage = new RoomImage();
			roomImage.setRoom(motelRoom);
			roomImage.setUrl(url);
			roomImages.add(roomImage);
		}

		roomImageRepo.saveAll(roomImages);
	}

	@Override
	public List<MotelRoom> getMotelRooms(String userName) {
		List<MotelRoom> rooms = repo.findByUserName(userName);
		return rooms;
	}

	@Override
	public MotelRoom getMotelRoomById(Integer id) throws Exception {
		MotelRoom room = repo.findById(id).orElseThrow(() -> new NotFoundException("Room not found"));
		return room;
	}

	@Override
	public Page<MotelRoom> searchRooms(Filter filter) {
		Pageable pageAble = PageRequest.of(filter.getPageNum(), filter.getPageSize());

		double minPrice = 0;
		if (filter.getMinPrice() > minPrice) {
			minPrice = filter.getMinPrice();
		}
		Specification<MotelRoom> spec = MotelRoomSpecification.priceGreaterOrEqual(minPrice);


		double maxPrice = Double.MAX_VALUE;

		if (filter.getMaxPrice() < maxPrice && filter.getMaxPrice() > minPrice) {
			maxPrice = filter.getMaxPrice();
		}
		spec = Specification.where(spec).and(MotelRoomSpecification.priceLessThanOrEqual(maxPrice));
		
		
		double minArea = 0;
		
		if (filter.getMinArea() > minArea) {
			minArea = filter.getMinArea();
		}
		
		spec = spec.and(MotelRoomSpecification.areaGreaterOrEqual(minArea));
		
		double maxArea = Double.MAX_VALUE;
		
		if (filter.getMaxArea() < maxArea && filter.getMaxArea() > minArea) {
			maxArea = filter.getMaxArea();
			spec = spec.and(MotelRoomSpecification.areaLessThanOrEqual(maxArea));
		}
		
		int numOfBedrooms = 0;
		if (filter.getNumOfBedrooms() > numOfBedrooms) {
			numOfBedrooms = filter.getNumOfBedrooms();
			spec = spec.and(MotelRoomSpecification.numOfBedroomsEqual(numOfBedrooms));
		}
		
		int numOfToilets = 0;
		if (filter.getNumOfToilets() > numOfToilets) {
			numOfToilets = filter.getNumOfToilets();
			spec = spec.and(MotelRoomSpecification.numOfToiletsEqual(numOfToilets));
		}
		
		if (null != filter.getBalconyDirection() && StringUtils.hasText(filter.getBalconyDirection())) {			
			spec = spec.and(MotelRoomSpecification.balconyDirectionEqual(filter.getBalconyDirection()));
		}
		
		if (null != filter.getDoorDirection() && StringUtils.hasText(filter.getDoorDirection())) {
			spec = spec.and(MotelRoomSpecification.doorDirectionEqual(filter.getDoorDirection()));
		}
		
		if (StringUtils.hasText(filter.getCategory())) {
			spec = spec.and(MotelRoomSpecification.categoryEqual(filter.getCategory()));
		}
		
		
		String wardCode;
		if (StringUtils.hasText(filter.getWardCode())) {
			wardCode = filter.getWardCode();
		
		
			spec = spec.and(MotelRoomSpecification.wardCodeEqual(wardCode));
		}
		
		if (StringUtils.hasText(filter.getDistrictCode())) {
			spec = spec.and(MotelRoomSpecification.districtCodeEqual(filter.getDistrictCode()));
		}
		if (StringUtils.hasText(filter.getProvinceCode())) {
			spec = spec.and(MotelRoomSpecification.provinceCodeEqual(filter.getProvinceCode()));
		}
		
		return repo.findAll(spec, pageAble);
	}

}
