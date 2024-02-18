package com.techurate.api.utilites.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder=true)
@AllArgsConstructor
@NoArgsConstructor
public class ChangeRequest {
	private String short_description;
	private String description;
}


