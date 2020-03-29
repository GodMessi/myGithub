package com.xms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xms.annotation.MyAnnotation;
import com.xms.entity.Car;
import com.xms.entity.Course;
import com.xms.entity.CourseContent;
import com.xms.entity.CourseDirection;
import com.xms.entity.Item;
import com.xms.entity.Order;

@MyAnnotation
public interface MainDao {
	// 查询全部课程的方法
	List<CourseDirection> findAllCourseDirection();

	// 查询实战推荐课程（5个）
	List<Course> findAllDemoCourse();

	// 查询新手课程（10个）
	List<Course> findAllNewCourse();

	// 根据课程方向id查询对应课程内容
	List<CourseContent> findCourseContentByCourseDirectionId(@Param("courseDirectionId") int courseDirectionId);

	// 根据课程方向id和课程内容id查询对应的课程
	List<Course> findCourseByCourseDirectionIdAndCourseContentId(Map<String, Object> map);

	// 视频播放
	Course findCourseByCourseId(int courseId);

	// 根据用户id来查找购物车
	Car findCarByUserId(int userId);

	// 创建购物车
	void saveCar(Car car);

	// 根据当前购物车id查询此购物车全部商品的id
	List<Integer> findCourseIdByCarId(int carId);

	// 生成商品条目
	void saveItem(Item item);

	// 将商品条目添加到购物车
	void saveCarItem(Map<String, Object> map);

	// 根据用户id查找相对应的购物车中商品条目
	List<Item> findItemByUserId(int userId);

	// 根据商品条目信息删除Car_item的的对应关系
	void deleteCarItem(int itemId);

	// 根据商品条目id删除商品条目
	void deleteItem(int itemId);

	// 根据商品条目查询商品条目信息
	Item findItemByItemId(int itemId);

	// 保存订单
	void saveOrder(Order order);

	// 将订单与商品条目关联
	void saveOrderItem(Map<String, Object> map);
}
