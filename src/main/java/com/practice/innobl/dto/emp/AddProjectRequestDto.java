package com.practice.innobl.dto.emp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AddProjectRequestDto {
    private Long id;
    private String projectId;
    private Long empId;
}
