package com.xms.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xms.entity.Car;
import com.xms.entity.Course;
import com.xms.entity.CourseContent;
import com.xms.entity.CourseDirection;
import com.xms.entity.Item;
import com.xms.entity.Order;
import com.xms.entity.User;
import com.xms.service.MainService;

@Controller
@RequestMapping("/main")
public class MainController {
	@Resource
	private MainService mainService;

	// 跳转至主页面
	@RequestMapping("/toIndex.do")
	public String toIndex(Model model) {

		// 查询全部课程方向
		List<CourseDirection> courseDirections = mainService.findAllCourseDirection();
		model.addAttribute("courseDirections", courseDirections);
		// 查询实战推荐课程(5个)

		List<Course> demoCourses = mainService.findCourse(2, 5);
		model.addAttribute("demoCourses", demoCourses);

		// 查询新手入门课程（10个）
		List<Course> newCourses = mainService.findCourse(1, 10);
		model.addAttribute("newCourses", newCourses);

		return "index";
	}

	// 根据课程号方向ID和课程内容ID 查询课程信息
	@RequestMapping("/toCourse.do")
	public String toCourse(@ModelAttribute("courseDirectionId") int courseDirectionId,
			@ModelAttribute("courseContentId") int courseContentId, Model model) {
		// 查询全部课程方向
		List<CourseDirection> courseDirections = mainService.findAllCourseDirection();
		model.addAttribute("courseDirections", courseDirections);

		// 根据课程方向id查询对应的课程内容
		List<CourseContent> courseContents = mainService.findCourseContentByCourseDirectionId(courseDirectionId);
		model.addAttribute("courseContents", courseContents);
		// 根据课程方向id和课程内容id查询对应的课程
		List<Course> courses = mainService.findCourseByCourseDirectionIdAndCourseContentId(courseDirectionId,
				courseContentId);
		model.addAttribute("courses", courses);
		return "course";
	}

	// 视频播放
	@RequestMapping("/toVideo.do")
	public String toVideo(int courseId, Model model) {
		// 根据课程id查找课程信息
		Course course = mainService.findCourseByCourseId(courseId);
		model.addAttribute("course", course);
		return "video";
	}

	// 购买
	@RequestMapping("/buy.do")
	@ResponseBody
	public boolean buy(int id, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		// 根据用户id查找其购物车，如果存在，直接用，如果没有，则创建
		Car car = mainService.findCarByUserId(user.getId());
		if (car == null) {
			car = new Car();
			car.setU_id(user.getId());
			mainService.saveCar(car);
		}
		// 根据当前购物车id查询购物车中全部商品的id
		List<Integer> ids = mainService.findCourseIdByCarId(car.getId());

		if (ids.contains(id)) {
			// 已购买过此商品
			return false;
		} else {
			// 没有购买过此商品
			Course course = mainService.findCourseByCourseId(id);
			// 生成商品条目
			Item item = new Item();
			item.setC_id(course.getId());
			item.setC_name(course.getName());
			item.setC_picture_url(course.getPicture_url());
			item.setC_price(course.getPrice());
			item.setAdd_time(new Timestamp(System.currentTimeMillis()));
			item.setRemove_time(null);

			mainService.saveItem(item);

			// 将此商品条目添加到购物车
			mainService.saveCarItem(car.getId(), item.getId());
			// 购买成功
			return true;
		}
	}

	// 显示购物车
	@RequestMapping("/toCar.do")
	public String toCar(HttpServletRequest request, Model model) {
		User user = (User) request.getSession().getAttribute("user");
		// 根据用户id找到相对应的购物车中的商品条目信息
		List<Item> items = mainService.findItemByUserId(user.getId());
		model.addAttribute("items", items);
		return "car";
	}

	// 删除商品条目
	@RequestMapping("/delete.do")
	public String delete(@RequestParam("item_id") int id) {
		// 从xc_car_item表中删除购物车与此商品条目的对应关系
		mainService.deleteCarItem(id);

		// 从xc_item表中删除对应的商品条目数据
		mainService.deleteItem(id);

		// 重定向到购物车页面
		return "redirect:/main/toCar.do";
	}

	// 批量删除
	@RequestMapping("/batchDelete.do")
	public String batchDelete(Integer[] itemIds) {
		for (int i = 0; i < itemIds.length; i++) {
			delete(itemIds[i]);
		}
		// 重定向到购物车页面
		return "redirect:/main/toCar.do";
	}

	// 跳转至订单页面
	@RequestMapping("/toOrder.do")
	public String toOrder(Integer[] itemIds, Model model) {
		// 计算购买商品的总价
		double totalPrice = 0.0;
		// 根据商品条目id查询商品条目
		List<Item> items = new ArrayList<Item>();
		for (Integer itemId : itemIds) {
			Item item = mainService.findItemByItemId(itemId);
			totalPrice += item.getC_price();
			items.add(item);
		}
		model.addAttribute("items", items);
		model.addAttribute("totalPrice", totalPrice);
		System.out.println(totalPrice);
		return "order";

	}

	// 提交訂單
	@RequestMapping("/order.do")
	public String order(Integer[] itemIds, Model model, HttpServletRequest request) {
		double totalPrice = 0.0;
		List<Item> items = new ArrayList<Item>();
		// 根據商品條目id將購買的商品條目信息從購物車刪除
		for (int i = 0; i < itemIds.length; i++) {
			Item item = mainService.findItemByItemId(itemIds[i]);
			// 計算訂單中商品總價
			totalPrice += item.getC_price();
			mainService.deleteCarItem(item.getId());
			items.add(item);
		}
		model.addAttribute("totalPrice", totalPrice);
		System.out.println(totalPrice);
		// 生成訂單
		Order order = new Order();
		order.setU_id(((User) (request.getSession().getAttribute("user"))).getId());
		order.setOrder_time(new Timestamp(System.currentTimeMillis()));
		// 保存訂單
		mainService.saveOrder(order);
		// 將訂單與商品條目關聯
		for (Item item : items) {
			mainService.saveOrderItem(order.getId(), item.getId());
		}
		return "paySuccess";
	}
}
