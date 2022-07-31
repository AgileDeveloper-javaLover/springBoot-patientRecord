package com.Blackpool.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Blackpool.Models.AuthenticationRequest;
import com.Blackpool.Models.AuthenticationResponse;
import com.Blackpool.Models.Patient;
import com.Blackpool.Repository.PatientRepository;
import com.Blackpool.Security.JwtService;
import com.Blackpool.Security.MyUserDetailService;

import io.swagger.models.Response;

@RestController
@RequestMapping("/api")
public class PatientController {
	@Autowired
	private PatientRepository patientRepo;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailService myUserDetailService;
	
	@Autowired
	private JwtService jwtService;
	
	@GetMapping("/")
	public List<Patient> allPatient() {
		return patientRepo.findByOrderByAppointmentAsc();
	}

	@GetMapping("/{id}")
	public Patient getById(@PathVariable("id") int id) {
		return patientRepo.findById(id).orElse(null);
	}

	@PostMapping("/")
	public Patient save(@RequestBody Patient p) {
		return patientRepo.save(p);
	}
	
	@PutMapping("/")
	public Patient update(@RequestBody Patient p) {
		return patientRepo.save(p);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") int id) {
		 patientRepo.deleteById(id);
	}
	
	@PostMapping("/auth")
	public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest ar) throws Exception{

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(ar.getUsername(), ar.getPassword()));
			
		}
		catch (BadCredentialsException ex) {
			throw new Exception("Invalid user credential");
		}
		
		final UserDetails userDetails=myUserDetailService.loadUserByUsername(ar.getUsername());
		
		final String token=jwtService.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(token));
	}
	
}
