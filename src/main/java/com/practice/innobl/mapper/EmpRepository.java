package com.practice.innobl.mapper;

import com.practice.innobl.dto.commoncode.CommondefinitionDto;
import com.practice.innobl.dto.emp.EmailCheckRequestDto;
import com.practice.innobl.dto.emp.EmpRequestDto;
import com.practice.innobl.dto.emp.EmpResponseDto;
import com.practice.innobl.dto.emp.SearchRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface EmpRepository {

    // 직원 목록 창 이동
    List<EmpResponseDto> getEmpList(RowBounds rowBounds, SearchRequest searchRequest);

    // 직원 목록 -> 직원 상태 변경
    int updateSt(EmpRequestDto empRequestDto);

    // 전체 직원 수 확인
    int getListCount(SearchRequest searchRequest);

    // 등록 창 이동시 필요 정보 가지고 오기
    List<CommondefinitionDto> getAddOptions();

    // 직원 등록
    int saveEmp(EmpRequestDto empRequestDto);

    // 직원 등록 및 수정 시, 이메일 중복 확인
    int checkEmail(EmailCheckRequestDto emailCheck);

    // 직원 상세내역 조회
    List<EmpResponseDto> empDetail(Long id);

    // 직원 정보 수정
    int updateEmp(EmpRequestDto empRequestDto);

    // 사용중이지 않는 이미지 삭제
    List<String> getImgList();


}