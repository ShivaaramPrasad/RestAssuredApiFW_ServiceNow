package com.techurate.api.utilites.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder=true)
@AllArgsConstructor
@NoArgsConstructor
public class AddEmployee {
	private String name;
	private String salary;
	private String age;
}


