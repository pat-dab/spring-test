/*
 * Copyright 2016-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.org.angular.rest;

import java.util.Collection;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import pl.org.angular.dto.AddDto;
import pl.org.angular.model.Add;
import pl.org.angular.service.AddService;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/api/adds")
public class AddRestController {

	private AddService addService;

	@Autowired
	public AddRestController(AddService addService) {
		super();
		this.addService = addService;
	}

	@GetMapping(value = "/lastname/{lastName}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<Add>> getAddsList(@PathVariable("lastName") String contactSurname) {
		if (contactSurname == null) {
			contactSurname = "";
		}
		Collection<Add> adds = this.addService.findAddByLastName(contactSurname);
		if (adds.isEmpty()) {
			return new ResponseEntity<Collection<Add>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Add>>(adds, HttpStatus.OK);
	}

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<Add>> getAdds() {

		Collection<Add> adds = this.addService.findAllAdds();
		if (adds.isEmpty()) {
			return new ResponseEntity<Collection<Add>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Add>>(adds, HttpStatus.OK);

	}

	@GetMapping(value = "/{addId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Add> getAdd(@PathVariable("addId") int addId) {

		Add add = null;
		add = this.addService.findAddById(addId);
		if (add == null) {
			return new ResponseEntity<Add>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Add>(add, HttpStatus.OK);

	}

	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Add> addAdd(@RequestBody @Valid AddDto addDto, BindingResult bindingResult,
			UriComponentsBuilder ucBuilder) {

		HttpHeaders headers = new HttpHeaders();
		if (addDto == null) {
			return new ResponseEntity<Add>(headers, HttpStatus.BAD_REQUEST);
		}
		Mapper mapper = DozerBeanMapperBuilder.buildDefault();
		Add newAdd = mapper.map(addDto, Add.class);

		this.addService.saveAdd(newAdd);
		headers.setLocation(ucBuilder.path("/api/adds/{id}").buildAndExpand(newAdd.getId()).toUri());
		return new ResponseEntity<Add>(newAdd, headers, HttpStatus.CREATED);

	}

	@PutMapping(value = "/{addId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Add> updateAdd(@PathVariable("addId") int addId, @RequestBody @Valid AddDto addDto,
			BindingResult bindingResult, UriComponentsBuilder ucBuilder) {

		HttpHeaders headers = new HttpHeaders();
		if (addDto == null) {
			return new ResponseEntity<Add>(headers, HttpStatus.BAD_REQUEST);
		}
		Add currentAdd = this.addService.findAddById(addId);
		if (currentAdd == null) {
			return new ResponseEntity<Add>(HttpStatus.NOT_FOUND);
		}

		Mapper mapper = DozerBeanMapperBuilder.buildDefault();
		Add newAdd = mapper.map(addDto, Add.class);
		mapper.map(newAdd, currentAdd);
		this.addService.saveAdd(currentAdd);
		return new ResponseEntity<Add>(currentAdd, HttpStatus.NO_CONTENT);

	}

	@DeleteMapping(value = "/{addId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Transactional
	public ResponseEntity<Void> deleteAdd(@PathVariable("addId") int addId) {

		Add add = this.addService.findAddById(addId);
		if (add == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		this.addService.deleteAdd(add);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

	}

}
