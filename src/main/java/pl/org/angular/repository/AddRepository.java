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
package pl.org.angular.repository;

import java.util.Collection;

import pl.org.angular.model.Add;

public interface AddRepository {

	Collection<Add> findByLastName(String lastName);

	Add findById(int id);

	void save(Add add);

	Collection<Add> findAll();

	void delete(Add add);

}
