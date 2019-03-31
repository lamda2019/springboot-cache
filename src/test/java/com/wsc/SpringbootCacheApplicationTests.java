package com.wsc;

import com.wsc.bean.Employee;
import com.wsc.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootCacheApplicationTests {
	@Autowired
	EmployeeMapper employeeMapper;

	@Test
	public void contextLoads() {
		Employee empById = employeeMapper.getEmpById(1);
		System.out.println(empById);
	}

}
