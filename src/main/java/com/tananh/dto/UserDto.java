package com.tananh.dto;

import lombok.*;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
	private Integer id;
    private String userName;
    private String name;
    private String email;
    private String imageURL;
	
    
}
