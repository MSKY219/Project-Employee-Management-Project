package com.practice.innobl.dto.emp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class EmpRequestDto { // 직원 등록
    private Long id;
    private String empName;
    private String addrMain;
    private String addrDetail;
    private String tel;
    private String email;
    private String img;
    private String enterAt;
    private String resignAt;
    private Long gender;
    private Long jobTitle;
    private Long dept;
    private Long empStatus;
    private Long skill;
    private Long grade;
    private Long education;
    private String regNo;
}
