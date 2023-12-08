package com.practice.innobl.dto.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectResponseDto {
    private String id;
    private String pTitle;
    private String startAt;
    private String endAt;
    private String place;
    private String skill;
    private String client;
    private String status;
    private String role;
    private String pId;
    private String empName;
    private String jobTitle;
    private String grade;
}
