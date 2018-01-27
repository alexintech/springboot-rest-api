package com.lsnotes.sandbox.springboot.restapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(path = "saver")
public class SaverController {

	private static final List<String> SAVED_VALUES = new ArrayList<>();

	@RequestMapping(path = "", method = POST)
	public ResponseEntity<?> writeValue(final @RequestBody Map<String, Object> input) {
		if (input.containsKey("value") && input.get("value") != null) {
			SAVED_VALUES.add(input.get("value").toString());
			return ok(SAVED_VALUES);
		}
		else
			return badRequest().body("'value' is required.");
	}
}
