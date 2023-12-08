package com.practice.innobl.dto.emp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class EmpDetailDeleteRequestDto {
    private Long id;
    private String projectId;
    private Long empId;
}
