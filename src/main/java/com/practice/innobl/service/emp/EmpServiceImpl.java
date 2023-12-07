package com.practice.innobl.service.emp;

import com.practice.innobl.dto.commoncode.CommondefinitionDto;
import com.practice.innobl.dto.commoncode.Pagination;
import com.practice.innobl.dto.emp.EmailCheckRequestDto;
import com.practice.innobl.dto.emp.EmpRequestDto;
import com.practice.innobl.dto.emp.EmpResponseDto;
import com.practice.innobl.dto.emp.SearchRequest;
import com.practice.innobl.dto.project.ProjectResponseDto;
import com.practice.innobl.mapper.EmpRepository;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmpServiceImpl implements EmpService{

    private final EmpRepository empRepository;

    // 직원 목록 창 이동
    @Override
    public Map<String, Object> getEmpList(int cp, SearchRequest searchRequest) {

        int listCount = empRepository.getListCount(searchRequest);

        Pagination pagination = new Pagination(cp, listCount);
        int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
        RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

        List<EmpResponseDto> selectedList = empRepository.getEmpList(rowBounds, searchRequest);

        Map<String, Object> getEmpList = new HashMap<String, Object>();
        getEmpList.put("pagination", pagination);
        getEmpList.put("getEmpList", selectedList);
        return getEmpList;
    }

    // 직원 목록 -> 직원 상태 변경
    @Override
    public int updateSt(EmpRequestDto empRequestDto) {
        return empRepository.updateSt(empRequestDto);
    }

    // 등록 창 이동시 필요 정보 가지고 오기
    @Override
    public List<CommondefinitionDto> getAddOptions() {
        return empRepository.getAddOptions();
    }

    // 직원 등록
    @Override
    public int saveEmp(EmpRequestDto empRequestDto) {
        return empRepository.saveEmp(empRequestDto);
    }

    // 직원 등록 및 수정 시, 이메일 중복 확인
    @Override
    public int checkEmail(EmailCheckRequestDto emailCheck) {
        int count = empRepository.checkEmail(emailCheck);
        return count;
    }

    // 직원 상세내역 조회
    @Override
    public List<EmpResponseDto> empDetail(Long id) {
        return empRepository.empDetail(id);
    }

    // 직원 정보 수정
    @Override
    public int updateEmp(EmpRequestDto empRequestDto) {

        System.out.println("직원 정보 수정까지 도달함");
        
        return empRepository.updateEmp(empRequestDto);
    }

    // 사용중이지 않는 이미지 삭제
    @Override
    public List<String> getImgList() {
        return empRepository.getImgList();
    }

    // 직원 상세내역 참여 프로젝트
    @Override
    public List<ProjectResponseDto> getProjectDetail(Long id) {
        return empRepository.getProjectDetail(id);
    }
}