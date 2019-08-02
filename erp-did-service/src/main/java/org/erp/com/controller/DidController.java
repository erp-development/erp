package org.erp.com.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.erp.com.entity.Did;
import org.erp.com.exception.DidNotFoundException;
import org.erp.com.service.DidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/dids")
public class DidController {

	@Autowired
	private DidService didService;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Did>> findAll() {
		List<Did> didList = didService.findAll();
		System.out.println(didList.size());
		return ResponseEntity.ok(didList);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Did> create(@RequestBody Did did) {
		didService.save(did);
		URI url = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(did.getDid()).toUri();
		// Send location in response
		return ResponseEntity.created(url).build();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{did}", produces = "application/json")
	public ResponseEntity<Did> getDid(@PathVariable String did) throws DidNotFoundException {
		Optional<Did> did_obj = didService.findById(did);
		if (!did_obj.isPresent()) {
			throw new DidNotFoundException("Id : " + did);
		}
		return ResponseEntity.ok(did_obj.get());
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/{did}")
	public void deleteDid(@PathVariable String did) {
		didService.deleteById(did);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/{did}")
	public ResponseEntity<Did> updateDid(@RequestBody Did did) throws DidNotFoundException {
		Optional<Did> did_obj = didService.findById(did.getDid());
		if (!did_obj.isPresent()) {
			throw new DidNotFoundException("Id : " + did);
		}
		didService.save(did_obj.get());
		return ResponseEntity.noContent().build();
	}
}