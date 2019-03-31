package com.wsc.service;

import com.wsc.bean.Employee;
import com.wsc.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;


       /*
       *  几个属性：
        *      cacheNames/value：指定缓存组件的名字;将方法的返回结果放在哪个缓存中，是数组的方式，可以指定多个缓存；
        *
        *      key：缓存数据使用的key；可以用它来指定。默认是使用方法参数的值  1-方法的返回值
        *              编写SpEL； #i d;参数id的值   #a0  #p0  #root.args[0]
        *              getEmp[2]
        *
        *      keyGenerator：key的生成器；可以自己指定key的生成器的组件id
        *              key/keyGenerator：二选一使用;
        *
        *
        *      cacheManager：指定缓存管理器；或者cacheResolver指定获取解析器
        *
        *      condition：指定符合条件的情况下才缓存；
        *              ,condition = "#id>0"
        *          condition = "#a0>1"：第一个参数的值》1的时候才进行缓存
        *
        *      unless:否定缓存；当unless指定的条件为true，方法的返回值就不会被缓存；可以获取到结果进行判断
        *              unless = "#result == null"
        *              unless = "#a0==2":如果第一个参数的值是2，结果不缓存；
        *      sync：是否使用异步模式
       *
       * */
       @Cacheable(value = {"emp"}/*,keyGenerator = "myKeyGenerator",condition = "#a0>1",unless = "#a0==2"*/)
       public Employee getEmp(Integer id) {
        System.out.println("查询" + id + "号员工");
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }

    /**
     * @CachePut：既调用方法，又更新缓存数据；同步更新缓存
     * 修改了数据库的某个数据，同时更新缓存；
     * 运行时机：
     *  1、先调用目标方法
     *  2、将目标方法的结果缓存起来
     *
     * 测试步骤：
     *  1、查询1号员工；查到的结果会放在缓存中；
     *          key：1  value：lastName：张三
     *  2、以后查询还是之前的结果
     *  3、更新1号员工；【lastName:zhangsan；gender:0】
     *          将方法的返回值也放进缓存了；
     *          key：传入的employee对象  值：返回的employee对象；
     *  4、查询1号员工？
     *      应该是更新后的员工；
     *       @CachePut(value = "emp",key = "#result.id")
     *          key = "#employee.id":使用传入的参数的员工id；
     *          key = "#result.id"：使用返回后的id
     *             @Cacheable的key是不能用#result
     *      为什么是没更新前的？【1号员工没有在缓存中更新】
     *
     */
    @CachePut(value = "emp",key = "#result.id")
    public Employee updateEmp(Employee employee){
        System.out.println("updateEmp:"+employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }
}