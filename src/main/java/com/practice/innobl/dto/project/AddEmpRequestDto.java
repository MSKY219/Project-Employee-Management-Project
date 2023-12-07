package com.practice.innobl.dto.project;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AddEmpRequestDto {
    private Long id;
    private String employeeId;
    private Long projectId;
    private Long status;
    private String startAt;
    private String endAt;
    private Long role;
}
