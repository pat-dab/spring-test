package pl.org.angular.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddDto {
	private Integer id;
	private String addTitle;
	private String addDescription;
	private String category;
	private String type;
	private String price;
	private String roomNumber;
	private String contactName;
	private String contactSurname;
	private String telephone;

}
