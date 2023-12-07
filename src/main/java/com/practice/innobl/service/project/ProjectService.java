package com.practice.innobl.service.project;

import com.practice.innobl.dto.emp.SearchRequest;
import com.practice.innobl.dto.project.AddEmpRequestDto;
import com.practice.innobl.dto.project.ProjectRequestDto;
import com.practice.innobl.dto.project.ProjectResponseDto;

import java.util.List;
import java.util.Map;

public interface ProjectService {
    // 프로젝트 등록
    int saveProject(ProjectRequestDto projectRequestDto);

    // 프로젝트 조회 창 이동
    Map<String, Object> getProjectList(int cp, SearchRequest searchRequest);

    // 프로젝트 목록 -> 직원 상태 변경
    int updateSt(ProjectRequestDto projectRequestDto);

    // 프로젝트 상세내역 조회
    List<ProjectResponseDto> getProjectDetail(String id);

    // 프로젝트 정보 수정
    int updateProject(ProjectRequestDto projectRequestDto);

    // 프로젝트 직원 추가
    int addEmp(AddEmpRequestDto addEmpRequestDto);

    // 프로젝트 상세내역 직원 조회
    Map<String, Object> getEmpList(int cp, SearchRequest searchRequest);
}
