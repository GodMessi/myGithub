create database xmscode;
use xmscode;
set names gbk;

#User表
create table xc_user(
	id int(4) primary key auto_increment,
	email varchar(50) not null unique key,
	password varchar(10) not null,
	nickname varchar(40) not null,
	registedate timestamp not null
);
#drop table xc_user;
insert into xc_user values(null,'7895624@163.com','123456','孙悟空',now());
select * from xc_user;

#课程方向表
create table xc_course_direction(
	id int(4) primary key auto_increment,
	name varchar(10) not null unique key,
	picture_url varchar(20) not null 
);
select * from xc_course_direction;
insert into xc_course_direction values(1,'JAVA开发','../images/java.png');
insert into xc_course_direction values(2,'UI设计','../images/ui.png');
insert into xc_course_direction values(3,'WEB开发','../images/web.png');
insert into xc_course_direction values(4,'网络工程','../images/net.png');
insert into xc_course_direction values(5,'数据库','../images/db.png');
insert into xc_course_direction values(6,'移动开发','../images/ios.png');
insert into xc_course_direction values(7,'大数据','../images/bd.png');
	select * from xc_course_direction;
	#delete from xc_course_direction where id= 4;
#课程内容表
create table xc_course_content(
	id int(4) primary key auto_increment,
	name varchar(10) not null unique key
);
#drop table xc_course_content;
select * from xc_course_content;
insert into xc_course_content values(1,'基础');
insert into xc_course_content values(2,'案例');
insert into xc_course_content values(3,'框架');
insert into xc_course_content values(4,'工具');
insert into xc_course_content values(5,'设计模式');

#课程方向-课程内容关联表
create table xc_course_direction_content(
	cd_id int(4), 
	cc_id int(4),
	primary key(cd_id,cc_id)
);
select * from xc_course_direction_content;
insert into xc_course_direction_content values(1,1);
insert into xc_course_direction_content values(1,2);
insert into xc_course_direction_content values(1,3);
insert into xc_course_direction_content values(1,4);
insert into xc_course_direction_content values(1,5);
insert into xc_course_direction_content values(2,1);
insert into xc_course_direction_content values(2,2);
insert into xc_course_direction_content values(3,1);
insert into xc_course_direction_content values(3,2);
insert into xc_course_direction_content values(3,3);
insert into xc_course_direction_content values(3,4);
insert into xc_course_direction_content values(4,1);
insert into xc_course_direction_content values(4,2);
insert into xc_course_direction_content values(4,4);
insert into xc_course_direction_content values(5,1);
insert into xc_course_direction_content values(5,2);
insert into xc_course_direction_content values(5,4);
insert into xc_course_direction_content values(6,1);
insert into xc_course_direction_content values(6,2);
insert into xc_course_direction_content values(6,3);
insert into xc_course_direction_content values(6,4);
insert into xc_course_direction_content values(6,5);
insert into xc_course_direction_content values(7,1);
insert into xc_course_direction_content values(7,2);
insert into xc_course_direction_content values(7,3);

#课程表
create table xc_course(
	id int(4) primary key auto_increment,
	name varchar(10) not null unique key,
	direction_id int(4) references xc_course_direction(id),
	content_id int(4) references xc_course_content(id),
	description varchar(20) not null,
	price double(5,2),
	picture_url varchar(30),
	video_url varchar(30),
	uploader varchar(20),
	uploadtime timestamp,
	updater varchar(10),
	updatetime timestamp
);
select * from xc_course;
insert into xc_course values(1,'面向对象',1,1,
	'面向对象比较抽象',199.0,'../images/xc_course/1-1.jpg',
		'../video/oceans-clip.mp4','张三',now(),null,null);
insert into xc_course values(2,'SE高级编程',1,1,
	'SE高级编程',99.0,'../images/xc_course/1-1.jpg',
		'../video/oceans-clip.mp4','张三',now(),null,null);	
insert into xc_course values(3,'在线考试系统',1,2,
	'SE项目案例',199.0,'../images/xc_course/1-1.jpg',
		'../video/oceans-clip.mp4','张三',now(),null,null);
insert into xc_course values(4,'Spring基础',1,3,
	'Spring基础',0.0,'../images/xc_course/4-1.jpg',
		'../video/oceans-clip.mp4','张三',now(),null,null);		
insert into xc_course values(5,'Maven项目构建',1,4,
	'Maven项目构建',199.0,'../images/xc_course/1-1.jpg',
		'../video/oceans-clip.mp4','张三',now(),null,null);	
insert into xc_course values(6,'单例模式',1,5,
	'设计模式',0.0,'../images/xc_course/1-1.jpg',
		'../video/oceans-clip.mp4','张三',now(),null,null);	
insert into xc_course values(7,'GUI基础',2,1,
	'PS设计课',199.0,'../images/xc_course/GUI.jpg',
		'../video/oceans-clip.mp4','张三',now(),null,null);	
insert into xc_course values(8,'AUI基础',2,1,
	'AUI基础',99.0,'../images/xc_course/AUI.jpg',
		'../video/oceans-clip.mp4','张三',now(),null,null);		
insert into xc_course values(9,'AUI案例课程',2,2,
	'AUI案例课程',199.0,'../images/xc_course/AUI.jpg',
		'../video/oceans-clip.mp4','张三',now(),null,null);	
insert into xc_course values(10,'HTML/CSS',3,1,
	'HTML/CSS/JS',299.0,'../images/xc_course/11.jpg',
		'../video/oceans-clip.mp4','张三',now(),null,null);	
insert into xc_course values(11,'JavaScript',3,1,
	'JavaScript',99.0,'../images/xc_course/11.jpg',
		'../video/oceans-clip.mp4','张三',now(),null,null);		
insert into xc_course values(12,'HTML5案例',3,2,
	'HTML5案例',199.0,'../images/xc_course/22.jpg',
		'../video/oceans-clip.mp4','张三',now(),null,null);	
insert into xc_course values(13,'Bootstrap',3,3,
	'Bootstrap',199.0,'../images/xc_course/33.jpg',
		'../video/oceans-clip.mp4','张三',now(),null,null);	
insert into xc_course values(14,'Sublime工具',3,4,
	'Sublime工具',0.0,'../images/xc_course/44.jpg',
		'../video/oceans-clip.mp4','张三',now(),null,null);	
insert into xc_course values(15,'智能DNS',4,1,
	'智能DNS',399.0,'../images/xc_course/22.jpg',
		'../video/oceans-clip.mp4','张三',now(),null,null);	
insert into xc_course values(16,'Bind负载均衡',4,2,
	'Bind负载均衡',199.0,'../images/xc_course/33.jpg',
		'../video/oceans-clip.mp4','张三',now(),null,null);	
insert into xc_course values(17,'Linux系统管理',4,4,
	'Linux系统管理',0.0,'../images/xc_course/44.jpg',
		'../video/oceans-clip.mp4','张三',now(),null,null);		
insert into xc_course values(18,'Oracle',5,1,
	'Oracle数据库',199.0,'../images/xc_course/2-1.jpg',
		'../video/oceans-clip.mp4','张三',now(),null,null);
insert into xc_course values(19,'MySQL',5,1,
	'MySQL数据库',99.0,'../images/xc_course/1-1.jpg',
		'../video/oceans-clip.mp4','张三',now(),null,null);															
insert into xc_course values(20,'MySQL实战',5,2,
	'复制功能实战',199.0,'../images/xc_course/1-1.jpg',
		'../video/oceans-clip.mp4','张三',now(),null,null);	
insert into xc_course values(21,'Android播放器',6,1,
	'Android视频播放器',0.0,'../images/xc_course/1-1.jpg',
		'../video/oceans-clip.mp4','张三',now(),null,null);	
insert into xc_course values(22,'IOS播放器',6,1,
	'IOS播放器',199.0,'../images/xc_course/1-1.jpg',
		'../video/oceans-clip.mp4','张三',now(),null,null);		
insert into xc_course values(23,'Android记账本',6,2,
	'Android记账本',299.0,'../images/xc_course/1-1.jpg',
		'../video/oceans-clip.mp4','张三',now(),null,null);	
insert into xc_course values(24,'GreenDao',6,3,
	'Android框架-GreenDao',199.0,'../images/xc_course/1-1.jpg',
		'../video/oceans-clip.mp4','张三',now(),null,null);							
insert into xc_course values(25,'自动更新组件开发',6,4,
	'自动更新组件开发',0.0,'../images/xc_course/1-1.jpg',
		'../video/oceans-clip.mp4','张三',now(),null,null);
insert into xc_course values(26,'IOS架构',6,5,
	'IOS架构',299.0,'../images/xc_course/1-1.jpg',
		'../video/oceans-clip.mp4','张三',now(),null,null);	
insert into xc_course values(27,'Hadoop进阶',7,1,
	'Hadoop进阶',0.0,'../images/xc_course/1-1.jpg',
		'../video/oceans-clip.mp4','张三',now(),null,null);	
insert into xc_course values(28,'Spark进阶',7,1,
	'Spark进阶',299.0,'../images/xc_course/1-1.jpg',
		'../video/oceans-clip.mp4','张三',now(),null,null);		
insert into xc_course values(29,'Hive实现用户画像',7,2,
	'Hive实现用户画像',199.0,'../images/xc_course/1-1.jpg',
		'../video/oceans-clip.mp4','张三',now(),null,null);			
insert into xc_course values(30,'KVM虚拟化技术',7,3,
	'KVM虚拟化技术',199.0,'../images/xc_course/1-1.jpg',
		'../video/oceans-clip.mp4','张三',now(),null,null);			

#购物车表
create table xc_car(
	id int(4) primary key auto_increment,
	u_id int(4) references user(id)
);
desc xc_car;
#订单表
create table xc_order(
	id int(11) primary key auto_increment,
	u_id int(4) references user(id),
	order_time timestamp,
	total_price double(7,2),
	pay_type char(1)
);
desc xc_order;
#商品条目表
create table xc_item(
	id int(4) primary key auto_increment,
	c_id	int(4) references xc_course(id),
	c_name varchar(10) not null,
	c_picture_url varchar(30),
	c_price double(5,2),
	add_time timestamp,
	remove_time timestamp	
);
desc xc_item;
#购物车-商品条目表
create table xc_car_item(
	c_id int(4),
	i_id int(4),
	primary key(c_id,i_id)
);
select * from xc_item;
desc xc_car_item;
#订单表-商品条目表
create table xc_order_item(
	o_id int(11),
	i_id int(4),
	primary key(o_id,i_id)
);
desc xc_order_item;