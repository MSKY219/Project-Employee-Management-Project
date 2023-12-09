package com.practice.innobl.dto.project;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectEditRequestDto {
    private List<ProjectRequestDto> empDataArr;
}