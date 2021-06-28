package com.ditcanada.health;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

	@GetMapping(value = "/health")
	public ResponseEntity<HealthResponse> getHealthStatus() {
		return ResponseEntity.ok(new HealthResponse(HealthStatus.UP));
	}
}
