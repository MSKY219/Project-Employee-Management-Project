package com.practice.innobl.dto.emp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmpResponseDto { // 직원 조회
    private Long id;
    private String empName;
    private String addrMain;
    private String addrDetail;
    private String tel;
    private String email;
    private String img;
    private String enterAt;
    private String resignAt;
    private String gender;
    private String jobTitle;
    private String dept;
    private String empStatus;
    private String skill;
    private String grade;
    private String education;
    private String regNo;
    private String role;
}