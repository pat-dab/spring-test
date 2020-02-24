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
package pl.org.angular.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "adds")
@Getter
@Setter
public class Add {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "add_title")
	@NotEmpty
	private String addTitle;

	@Column(name = "add_description")
	@NotEmpty
	private String addDescription;

	@Column(name = "category")
	@NotEmpty
	private String category;

	@Column(name = "type_of_add")
	@NotEmpty
	private String type;

	@Column(name = "price")
	@NotEmpty
	private String price;

	@Column(name = "room_number")
	@NotEmpty
	private String roomNumber;

	@Column(name = "contact_name")
	@NotEmpty
	private String contactName;

	@Column(name = "contact_surname")
	@NotEmpty
	private String contactSurname;

	@Column(name = "telephone")
	@NotEmpty
	private String telephone;

}
