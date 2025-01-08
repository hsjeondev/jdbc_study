package com.gn.study.model.service;

import static com.gn.study.common.JDBCTemlpate.close;
import static com.gn.study.common.JDBCTemlpate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.gn.study.model.dao.Dao;
import com.gn.study.model.vo.Car;
// DB에 접속 -> Connection 객체 생성
public class Service {
	private Dao dao = new Dao();
	
	public int updateCarOne(int carNo, Object[] obj) {
		Connection conn = getConnection();
		int result = dao.updateCarOne(conn, carNo, obj);
		close(conn);
		return result;
	}
	
	public int deleteCarOne(int carNo) {
		Connection conn = getConnection();
		int result = dao.deleteCarOne(conn, carNo);
		close(conn);
		return result;
	}
	
	public List<Car> selectCars(String carDate){
		Connection conn = getConnection();
		List<Car> list = dao.selectCars(conn, carDate);
		close(conn);
		return list;
	}
	
	public List<Car> selectCars(int carPrice){
		Connection conn = getConnection();
		List<Car> list = dao.selectCars(conn, carPrice);
		close(conn);
		return list;
	}
	
	public Car selectCarOne(String modelName) {
		Connection conn = getConnection();
		Car car = dao.selectCarOne(conn, modelName);
		close(conn);
		return car;
	}
	
	public Car selectCarOne(int modelNo) {
		Connection conn = getConnection();
		Car car = dao.selectCarOne(conn, modelNo);
		close(conn);
		return car;
	}
	
	public List<Car> selectCarAll() {
		Connection conn = getConnection();
		List<Car> list = dao.selectCarAll(conn);
		close(conn);
		return list;
	}
	
	public int insertCarOne(Car car) {
		Connection conn = getConnection();
		int result = dao.insertCarOne(car, conn);
		close(conn);
		return result;
		
//		Connection conn = null;
//		int result = 0;
//		try {
//			Class.forName("org.mariadb.jdbc.Driver");
//			String url = "jdbc:mariadb://127.0.0.1:3306/jdbc_basic";
//			String id = "scott";
//			String pw = "tiger";
//			conn = DriverManager.getConnection(url, id, pw);
//			result = dao.insertCarOne(car, conn);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				if(conn != null) conn.close();
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return result;
	}
}
