package com.mss.demo.controller;

import java.util.Map;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.mss.demo.service.EmployeeService;

import ch.qos.logback.core.net.SyslogOutputStream;


@Component
public class Receiver {

	EmployeeService empService;

	@Autowired
	public Receiver(EmployeeService empService) {
		this.empService = empService;
	}

	@Bean
	Queue queue() {
		return new Queue("EmpMgmtQ", false);
	}

	@RabbitListener(queues = "EmpMgmtQ")
	public void processMessage(Map<String, Object> loc) {
		System.out.println("Message received to update "+loc.get("LOCATION_NUMBER"));
		empService.updateLocation((int) loc.get("LOCATION_NUMBER"),	(int) loc.get("EMPLOYEEID"));
	}
}