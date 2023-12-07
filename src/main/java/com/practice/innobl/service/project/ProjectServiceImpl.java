package com.practice.innobl.service.project;

import com.practice.innobl.dto.commoncode.Pagination;
import com.practice.innobl.dto.emp.EmpResponseDto;
import com.practice.innobl.dto.emp.SearchRequest;
import com.practice.innobl.dto.project.AddEmpRequestDto;
import com.practice.innobl.dto.project.ProjectRequestDto;
import com.practice.innobl.dto.project.ProjectResponseDto;
import com.practice.innobl.mapper.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;

    // 프로젝트 등록
    @Override
    public int saveProject(ProjectRequestDto projectRequestDto) {
        return projectRepository.saveProject(projectRequestDto);
    }

    // 프로젝트 조회 창 이동
    @Override
    public Map<String, Object> getProjectList(int cp, SearchRequest searchRequest) {

        int listCount = projectRepository.getListCount(searchRequest);

        Pagination pagination = new Pagination(cp, listCount);
        int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
        RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());


        List<EmpResponseDto> selectedList = projectRepository.getProjectList(rowBounds, searchRequest);

        Map<String, Object> getProjectList = new HashMap<String, Object>();
        getProjectList.put("pagination", pagination);
        getProjectList.put("getProjectList", selectedList);
        return getProjectList;
    }

    // 프로젝트 목록 -> 직원 상태 변경
    @Override
    public int updateSt(ProjectRequestDto projectRequestDto) {
        return projectRepository.updateSt(projectRequestDto);
    }

    // 프로젝트 상세내역 조회
    @Override
    public List<ProjectResponseDto> getProjectDetail(String id) {
        return projectRepository.getProjectDetail(id);
    }

    // 프로젝트 정보 수정
    @Override
    public int updateProject(ProjectRequestDto projectRequestDto) {
        return projectRepository.updateProject(projectRequestDto);
    }

    // 프로젝트 직원 추가
    @Override
    public int addEmp(AddEmpRequestDto addEmpRequestDto) {
        return projectRepository.addEmp(addEmpRequestDto);
    }

    // 프로젝트 상세내역 조회
    @Override
    public Map<String, Object> getEmpList(int cp, SearchRequest searchRequest) {

        int listCount = projectRepository.getEmpListCount(searchRequest);

        Pagination pagination = new Pagination(cp, listCount);
        int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
        RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

        List<EmpResponseDto> selectedList = projectRepository.getEmpList(rowBounds, searchRequest);

        Map<String, Object> getEmpList = new HashMap<String, Object>();
        getEmpList.put("pagination", pagination);
        getEmpList.put("getEmpList", selectedList);
        return getEmpList;
    }
}
