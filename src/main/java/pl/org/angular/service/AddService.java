/*
 * Copyright 2002-2017 the original author or authors.
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
package pl.org.angular.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.org.angular.model.Add;
import pl.org.angular.repository.AddRepository;

@Service
public class AddService {

	private AddRepository addRepository;

	@Autowired
	public AddService(AddRepository addRepository) {
		this.addRepository = addRepository;
	}

	@Transactional(readOnly = true)
	public Collection<Add> findAllAdds() {
		return addRepository.findAll();
	}

	@Transactional
	public void deleteAdd(Add add) {
		addRepository.delete(add);
	}

	@Transactional(readOnly = true)
	public Add findAddById(int id) {
		Add add = null;
		try {
			add = addRepository.findById(id);
		} catch (ObjectRetrievalFailureException | EmptyResultDataAccessException e) {
			return null;
		}
		return add;
	}

	@Transactional
	public void saveAdd(Add add) {
		addRepository.save(add);

	}

	@Transactional(readOnly = true)
	public Collection<Add> findAddByLastName(String contactSurname) {
		return addRepository.findByLastName(contactSurname);
	}

}
