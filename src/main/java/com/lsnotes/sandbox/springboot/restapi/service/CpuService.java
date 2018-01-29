package com.lsnotes.sandbox.springboot.restapi.service;

import com.lsnotes.sandbox.springboot.restapi.model.Cpu;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class CpuService {

	private static final String SELECT_LATEST = "select * from cpu order by time desc limit 1";

	@Autowired
	private InfluxDBTemplate<Point> influxDBTemplate;
	private InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();

	public void save(Cpu value) {
		Point p = Point.measurement("cpu")
		               .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
		               .addField("value", value.getValue())
		               .build();
		influxDBTemplate.write(p);
	}

	public Cpu getLatest() {
		Query query = new Query(SELECT_LATEST, influxDBTemplate.getDatabase());
		QueryResult result = influxDBTemplate.query(query);

		List<Cpu> cpuList = resultMapper.toPOJO(result, Cpu.class);
		return cpuList.isEmpty() ? null : cpuList.get(0);
	}
}
