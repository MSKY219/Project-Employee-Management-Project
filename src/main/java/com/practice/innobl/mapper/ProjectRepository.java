package com.practice.innobl.mapper;

import com.practice.innobl.dto.emp.AddProjectRequestDto;
import com.practice.innobl.dto.emp.EmpDetailDeleteRequestDto;
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

    // 프로젝트에서 직원 추가 및 직원 상세내역에서 프로젝트 추가하기
    int addEmp(AddEmpRequestDto addEmpRequestDto);

    // 프로젝트 상세내역 직원 수 조회
    int getEmpListCount(SearchRequest searchRequest);

    // 프로젝트 상세내역 직원 조회
    List<EmpResponseDto> getEmpList(RowBounds rowBounds, SearchRequest searchRequest);

    // 프로젝트 참여 직원 삭제
    int deleteEmp(AddEmpRequestDto addEmpRequestDto);

    // 직원 상세내역에서 프로젝트 추가하기
    int addProjectToEmp(AddProjectRequestDto addProjectRequestDto);

    // 직원 상세페이지에서 프로젝트 삭제
    int deleteProject(EmpDetailDeleteRequestDto empDetailDeleteRequestDto);

    // 프로젝트 세부 내역 속 직원 역할 및 투입일, 철수일 수정
    int editEmp(ProjectRequestDto empData);

    // 해당 프로젝트에 참가한 직원이 있는지 확인
    int checkEmpInside(Long pId);

    // 프로젝트안에 직원이 존재하지 않을 때 삭제
    void deleteProjectFromList(Long pId);
}
