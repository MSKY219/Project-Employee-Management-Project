package com.practice.innobl.dto.project;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProjectRequestDto {
    private Long id;
    private String pTitle;
    private String startAt;
    private String endAt;
    private String place;
    private Long skill;
    private Long client;
    private Long status;
    private String role;
    private Long projectId;
}
