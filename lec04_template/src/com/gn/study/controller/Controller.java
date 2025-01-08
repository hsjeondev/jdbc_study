package com.gn.study.controller;

import java.util.List;

import com.gn.study.model.service.Service;
import com.gn.study.model.vo.Car;

// View로부터 전달받은 데이터 가공 -> Service 전달
public class Controller {
	Service service = new Service();
	
	public int updateCarOne(int carNo, Object[] obj) {
		return service.updateCarOne(carNo, obj);
	}
	
	public int deleteCarOne(int carNo) {
		int result = service.deleteCarOne(carNo);
		return result;
	}
	
	public Car selectCarOne(String modelName) {
		return service.selectCarOne(modelName);
	}
	
	public Car selectCarOne(int modelNo) {
		return service.selectCarOne(modelNo);
	}
	
	public List<Car> selectCars(int carPrice) {
		return service.selectCars(carPrice);
	}
	
	public List<Car> selectCars(String carDate) {
		return service.selectCars(carDate);
	}
	
	public List<Car> selectCarAll() {
		return service.selectCarAll();
	}

	public int insertCarOne(String modelName, int price, String date) {
		Car car = new Car(modelName, price, date);
		int result = service.insertCarOne(car);
		return result;
	}
	
}
