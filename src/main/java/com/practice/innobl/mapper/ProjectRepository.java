package com.practice.innobl.mapper;

import com.practice.innobl.dto.emp.EmpResponseDto;
import com.practice.innobl.dto.emp.SearchRequest;
import com.practice.innobl.dto.project.AddEmpRequestDto;
import com.practice.innobl.dto.project.ProjectRequestDto;
import com.practice.innobl.dto.project.ProjectResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface ProjectRepository {

    // 프로젝트 등록
    int saveProject(ProjectRequestDto projectRequestDto);

    // 프로젝트 조회 창 이동
    List<EmpResponseDto> getProjectList(RowBounds rowBounds, SearchRequest searchRequest);

    // 프로젝트 목록 -> 직원 상태 변경
    int updateSt(ProjectRequestDto projectRequestDto);

    // 전체 프로젝트 수 확인
    int getListCount(SearchRequest searchRequest);

    // 프로젝트 상세내역 조회
    List<ProjectResponseDto> getProjectDetail(String id);

    // 프로젝트 정보 수정
    int updateProject(ProjectRequestDto projectRequestDto);

    // 프로젝트 직원 추가
    int addEmp(AddEmpRequestDto addEmpRequestDto);

    // 프로젝트 상세내역 직원 수 조회
    int getEmpListCount(SearchRequest searchRequest);

    // 프로젝트 상세내역 직원 조회
    List<EmpResponseDto> getEmpList(RowBounds rowBounds, SearchRequest searchRequest);
}
