package com.xms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xms.dao.MainDao;
import com.xms.entity.Car;
import com.xms.entity.Course;
import com.xms.entity.CourseContent;
import com.xms.entity.CourseDirection;
import com.xms.entity.Item;
import com.xms.entity.Order;

@Service
public class MainService {

	@Resource
	private MainDao mainDao;

	// 查询全部课程方向
	public List<CourseDirection> findAllCourseDirection() {
		List<CourseDirection> courseDirections = mainDao.findAllCourseDirection();
		return courseDirections;
	}
	/*
	 * //查询实战推荐课程(5个) public List<Course> findDemoCourse(){ List<Course> demoCourses
	 * = mainDao.findAllDemoCourse(); List<Course> listCourses = new
	 * ArrayList<Course>(); Random rd = new Random(); if(demoCourses.size()<=5){
	 * return demoCourses; }else{ for(int i=0;i<5;i++){ int index =
	 * rd.nextInt(demoCourses.size());
	 * 
	 * Course course = demoCourses.remove(index); listCourses.add(course); } }
	 * return listCourses; } //查询新手课程（10个） public List<Course> findNewCourse(){
	 * List<Course> newCourses = mainDao.findAllNewCourse(); List<Course>
	 * listNewCourses = new ArrayList<Course>(); Random rd = new Random();
	 * if(newCourses.size()<=10){ return newCourses; }else{ for(int i=0;i<10;i++){
	 * listNewCourses.add(newCourses.remove (rd.nextInt (newCourses.size()))); } }
	 * return listNewCourses; }
	 */

	public List<Course> findCourse(Integer content_id, Integer size) {
		List<Course> Courses = null;
		if (content_id == 1) {
			Courses = mainDao.findAllNewCourse();
		} else {
			Courses = mainDao.findAllDemoCourse();
		}
		List<Course> listNewCourses = new ArrayList<Course>();
		Random rd = new Random();
		if (Courses.size() <= size) {
			return Courses;
		} else {
			for (int i = 0; i < size; i++) {
				listNewCourses.add(Courses.remove(rd.nextInt(Courses.size())));
			}
		}
		return listNewCourses;

	}

	// 根据课程方向id查询对应课程内容
	public List<CourseContent> findCourseContentByCourseDirectionId(int courseDirectionId) {
		return mainDao.findCourseContentByCourseDirectionId(courseDirectionId);
	}

	// 根据课程方向id和课程内容id查询对应的课程
	public List<Course> findCourseByCourseDirectionIdAndCourseContentId(int courseDirectionId, int courseContentId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("courseDirectionId", courseDirectionId);
		map.put("courseContentId", courseContentId);
		return mainDao.findCourseByCourseDirectionIdAndCourseContentId(map);
	}

	// 视频播放
	public Course findCourseByCourseId(int courseId) {
		return mainDao.findCourseByCourseId(courseId);
	}

	// 根据用户id来查找购物车
	public Car findCarByUserId(int userId) {
		return mainDao.findCarByUserId(userId);
	}

	// 创建购物车
	public void saveCar(Car car) {
		mainDao.saveCar(car);
	}

	// 根据当前购物车id查询此购物车全部的商品的id
	public List<Integer> findCourseIdByCarId(int carId) {
		return mainDao.findCourseIdByCarId(carId);
	}

	// 生成商品条目
	public void saveItem(Item item) {
		mainDao.saveItem(item);
	}

	// 将商品条目添加到购物车
	public void saveCarItem(int carId, int itemId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("c_id", carId);
		map.put("i_id", itemId);
		mainDao.saveCarItem(map);
	}

	// 根据用户id查找相对应的购物车中商品条目
	public List<Item> findItemByUserId(int userId) {
		return mainDao.findItemByUserId(userId);
	}

	// 根据商品条目信息删除Car_item的的对应关系
	public void deleteCarItem(int itemId) {
		mainDao.deleteCarItem(itemId);
	}

	// 根据商品条目id来删除商品条目
	public void deleteItem(int itemId) {
		mainDao.deleteItem(itemId);
	}

	// 根据商品条目查询商品条目信息
	public Item findItemByItemId(int itemId) {
		return mainDao.findItemByItemId(itemId);
	}

	// 保存訂單
	public void saveOrder(Order order) {
		mainDao.saveOrder(order);
	}

	//
	// 将订单与商品条目关联
	public void saveOrderItem(int orderId, int itemid) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", orderId);
		map.put("itemId", itemid);

		mainDao.saveOrderItem(map);

	}

}
