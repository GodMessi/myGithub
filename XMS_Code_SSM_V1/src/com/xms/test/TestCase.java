package com.xms.test;

import java.util.List;

//import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xms.entity.Course;
import com.xms.entity.CourseDirection;
import com.xms.service.MainService;

public class TestCase {
	ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
	MainService mainService = ac.getBean("mainService", MainService.class);

	// @Test
	public void testOne() {
		List<CourseDirection> courseDirections = mainService.findAllCourseDirection();
		for (CourseDirection courseDirection : courseDirections) {
			System.out.println(courseDirection.getName());
			System.out.println(courseDirection.getPicture_url());
			System.out.println(courseDirection.getId());
			System.out.println("===========================");
		}
	}

	// @Test
	public void testTwo() {
		List<Course> courses = mainService.findCourse(2, 5);
		for (Course course : courses) {
			// System.out.println(course.getDescription());
			System.out.println(course.getName());
			System.out.println(course.getPicture_url());
			System.out.println(course.getUpdater());
			System.out.println(course.getUploader());
			System.out.println(course.getVideo_url());
			System.out.println(course.getContent_id());
			System.out.println(course.getDirection_id());
			System.out.println(course.getId());
			System.out.println(course.getPrice());
			System.out.println(course.getUpdatetime());
			System.out.println(course.getUploadtime());
			System.out.println("------------------------");
		}
	}

	// @Test
	public void testThree() {
		List<Course> courses = mainService.findCourse(1, 10);
		for (Course course : courses) {
			// System.out.println(course.getDescription());
			System.out.println(course.getName());
			System.out.println(course.getPicture_url());
			System.out.println(course.getUpdater());
			System.out.println(course.getUploader());
			System.out.println(course.getVideo_url());
			System.out.println(course.getContent_id());
			System.out.println(course.getDirection_id());
			System.out.println(course.getId());
			System.out.println(course.getPrice());
			System.out.println(course.getUpdatetime());
			System.out.println(course.getUploadtime());
			System.out.println("+++++++++++++++++++++++++++");
		}
	}
}
