package com.techprimers.security.jwtsecurity.controller;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.techprimers.security.jwtsecurity.model.JwtUser;
import com.techprimers.security.jwtsecurity.model.WorkHour;
import com.techprimers.security.jwtsecurity.service.UserService;
import com.techprimers.security.jwtsecurity.service.WorkHourService;

@RestController
@RequestMapping("/rest/workHours")
public class WorkHourController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WorkHourService workHourService;
	
	@PostMapping("/list/{userid}")
	@ResponseBody
	public Map<String, String> list(@PathVariable Long userid, @RequestParam String initialDate, @RequestParam String finishDate) throws ParseException{
		JwtUser user = (JwtUser) userService.findOne(userid);
		
		if(user == null){
			throw new RuntimeException("User not found");
		}
		
		SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
		Date initialDateFormat = formatterDate.parse(initialDate);
		Date finishDateFormat = formatterDate.parse(finishDate);
		
		return workHourService.findByWorkDateBetween(initialDateFormat, finishDateFormat, userid);
	}
	
	@PostMapping("/{userid}")
	@ResponseBody
	public WorkHour register(@RequestParam String workDate, @RequestParam String inputTime, 
			@RequestParam String outputTime, @PathVariable Long userid) throws ParseException {
		JwtUser user = (JwtUser) userService.findOne(userid);
		
		if(user == null){
			throw new RuntimeException("User not found");
		}
		
		SimpleDateFormat formatterHour = new SimpleDateFormat("HH:mm");
		SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
		Date inputTimeFormat = formatterHour.parse(inputTime);
		Date outputTimeFormat = formatterHour.parse(outputTime);
		Date workDateFormat = formatterDate.parse(workDate);
		
		WorkHour workHour = new WorkHour(workDateFormat, new Time(inputTimeFormat.getTime()), new Time(outputTimeFormat.getTime()), user);
		
		if(workDate != null && inputTime != null && outputTime != null){
			workHourService.save(workHour);
		}
		
		return workHour;
	}
}
