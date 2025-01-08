package com.gn.study.model.dao;

import static com.gn.study.common.JDBCTemlpate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.gn.study.model.vo.Car;

public class Dao {
	
	public int updateCarOne(Connection conn, int carNo ,Object[] obj) {
		int result = 0;
		
		String modelSql = "UPDATE car SET car_model = ? WHERE car_no = ?";
		String priceSql = "UPDATE car SET car_price = ? WHERE car_no = ?";
		String dateSql = "UPDATE car SET car_date = STR_TO_DATE(? ,'%Y-%m-%d') WHERE car_no = ?";
		
		PreparedStatement pstmt = null;
		try {
			if(obj[0] != null) {
				pstmt = conn.prepareStatement(modelSql);
				pstmt.setString(1, obj[0].toString());
				pstmt.setInt(2, carNo);
				result += pstmt.executeUpdate();
			}
			if((int)obj[1] != 0) {
				pstmt = conn.prepareStatement(priceSql);
				pstmt.setInt(1, (int)obj[1]);
				pstmt.setInt(2, carNo);
				result += pstmt.executeUpdate();
			}
			if(obj[2] != null) {
				pstmt = conn.prepareStatement(dateSql);
				pstmt.setString(1, obj[2].toString());
				pstmt.setInt(2, carNo);
				result += pstmt.executeUpdate();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int deleteCarOne(Connection conn, int carNo) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		try {
			String sql = "DELETE FROM car WHERE car_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, carNo);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public List<Car> selectCars(Connection conn, int carPrice) {
		List<Car> list = new ArrayList<Car>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM car WHERE car_price = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, carPrice);
			rs = pstmt.executeQuery();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			while(rs.next()) {
				Car car = new Car();
				car.setCarNo(rs.getInt("car_no"));
				car.setCarModel(rs.getString("car_model"));
				car.setCarPrice(rs.getInt("car_price"));
				
				if(rs.getDate("car_date") != null) {
					car.setCarDate(sdf.format(rs.getDate("car_date")));
				} else {
					car.setCarDate("(-)");
				}
				list.add(car);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs, pstmt);
		}
		
		return list;
	}
	
	public List<Car> selectCars(Connection conn, String carDate) {
		List<Car> list = new ArrayList<Car>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM car WHERE car_date = STR_TO_DATE(? ,'%Y-%m-%d')";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, carDate);
			rs = pstmt.executeQuery();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			while(rs.next()) {
				Car car = new Car();
				car.setCarNo(rs.getInt("car_no"));
				car.setCarModel(rs.getString("car_model"));
				car.setCarPrice(rs.getInt("car_price"));
				
				if(rs.getDate("car_date") != null) {
					car.setCarDate(sdf.format(rs.getDate("car_date")));
				} else {
					car.setCarDate("(-)");
				}
				list.add(car);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs, pstmt);
		}
		
		return list;
	}
	
	public Car selectCarOne(Connection conn, String modelName) {
		Car car = new Car();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM car WHERE car_model = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, modelName);
			rs = pstmt.executeQuery();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(rs.next()) {
				car.setCarNo(rs.getInt("car_no"));
				car.setCarModel(rs.getString("car_model"));
				car.setCarPrice(rs.getInt("car_price"));
				
				if(rs.getDate("car_date") != null) {
					car.setCarDate(sdf.format(rs.getDate("car_date")));
				} else {
					car.setCarDate("(-)");
				}
			} else {
				car = null;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs, pstmt);
		}
		
		return car;
	}
	
	public Car selectCarOne(Connection conn, int modelNo) {
		Car car = new Car();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM car WHERE car_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, modelNo);
			rs = pstmt.executeQuery();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(rs.next()) {
				car.setCarNo(rs.getInt("car_no"));
				car.setCarModel(rs.getString("car_model"));
				car.setCarPrice(rs.getInt("car_price"));
				
				if(rs.getDate("car_date") != null) {
					car.setCarDate(sdf.format(rs.getDate("car_date")));
				} else {
					car.setCarDate("(-)");
				}
			} else {
				car = null;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs, pstmt);
		}
		
		return car;
	}
	
	public List<Car> selectCarAll(Connection conn) {
		List<Car> list = new ArrayList<Car>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM car";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			while(rs.next()) {
//				Car car = new Car(rs.getInt("car_no"), 
//								  rs.getString("car_model"),
//								  rs.getInt("car_price"),
//								  sdf.format(rs.getDate("car_date")));
				Car car = new Car();
				car.setCarNo(rs.getInt("car_no"));
				car.setCarModel(rs.getString("car_model"));
				car.setCarPrice(rs.getInt("car_price"));
				
				if(rs.getDate("car_date") != null) {
					car.setCarDate(sdf.format(rs.getDate("car_date")));
				} else {
					car.setCarDate("(-)");
				}
				list.add(car);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs, pstmt);
		}
		
		return list;
	}

	public int insertCarOne(Car car, Connection conn) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		try {
			String sql = "INSERT INTO car(car_model ,car_price ,car_date) "
						+ " VALUES(? ,? ,STR_TO_DATE(? ,'%Y-%m-%d')) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, car.getCarModel());
			pstmt.setInt(2, car.getCarPrice());
			pstmt.setString(3, car.getCarDate());
			
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
}
