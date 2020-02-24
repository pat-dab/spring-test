/*
 * Copyright 2002-2013 the original author or authors.
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
package pl.org.angular.repository.springdatajpa;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import pl.org.angular.model.Add;
import pl.org.angular.repository.AddRepository;

public interface SpringDataAddRepository extends AddRepository, Repository<Add, Integer> {

	@Override
	@Query("SELECT DISTINCT add FROM Add add  WHERE add.contactSurname LIKE :contactSurname%")
	Collection<Add> findByLastName(@Param("contactSurname") String contactSurname);

	@Override
	@Query("SELECT add FROM Add add WHERE add.id =:id")
	Add findById(@Param("id") int id);
}
