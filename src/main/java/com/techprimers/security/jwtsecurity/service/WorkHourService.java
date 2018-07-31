package com.techprimers.security.jwtsecurity.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techprimers.security.jwtsecurity.model.JwtUser;
import com.techprimers.security.jwtsecurity.model.WorkHour;
import com.techprimers.security.jwtsecurity.repository.UserRepository;
import com.techprimers.security.jwtsecurity.repository.WorkHourRepository;

@Service
public class WorkHourService implements CrudService<WorkHour> {
	@Autowired
	private WorkHourRepository workHourRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@PersistenceContext
	public EntityManager em;
	
	@Override
	public <T> Object save(WorkHour entity) {
		return workHourRepository.save(entity);
	}

	@Override
	public <T> Object findOne(Long id) {
		return workHourRepository.findOne(id);
	}

	@Override
	public Iterable<WorkHour> findAll() {
		return workHourRepository.findAll();
	}
	
	public Map<String, String> findByWorkDateBetween(Date initialDate, Date finishDate, Long userid){
		
		Iterable<WorkHour> list = workHourRepository.findByWorkDateBetweenAndUserId(initialDate, finishDate, userid);
			
		long total = 0;
		for(WorkHour hour: list){
			total = total + hour.getOutputTime().getTime() - hour.getInputTime().getTime();
		}
		
		JwtUser user = userRepository.findOne(userid);
		
		Map<String,String> res = new HashMap<String,String>();
		res.put("user", user.getUserName());
		res.put("totalPerPeriod", (total / 3600000) + " hour/s " + (total % 3600000) / 60000 + " minutes");
		
		return res;
	}
}
