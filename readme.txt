VERSION
---------------
JDK: 1.8.0_111
tomcat: 1.8.5


DATABASE
---------------
mysql: 5.7.16



ubah configurasi database di:
spring/applicationContext.xml


include 2 project dalam satu workspace dan web server
-acs
-acsstatic

INTRUKSI INSTALLASI
-------------------
1. install jdk
2. install tomcat
3. install eclipse 
4. open eclipse
5. import project acs & acsstatic
6. setting build path : click kanan project->properties-> build path-> library -> JRE System Library -> tambahkan jdk 180_111
7. tambahkan web server tomcat 8.5
8. refresh project
9. click kanan project -> run -> maven clean
10. pada toolbar atas pilih project -> clean
11. ubah settingan database acs/src/spring/applicationContext.xml (sesuaikan dengan settingan mysql masing-masing)
	Contoh:

	<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
		<property name="driverClassName" value="com.mysql.jdbc.Driver" />
		<property name="url" value="jdbc:mysql://localhost:3306/acs" />
		<property name="username" value="root" />
		<property name="password" value="" />
	</bean>
12. Lakukan langkah nomor 10
13. tambah web server (tomcat) timeout value= 45->190
