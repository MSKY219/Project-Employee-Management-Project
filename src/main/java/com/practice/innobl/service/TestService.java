package com.practice.innobl.service;

import com.practice.innobl.dto.TestDto;

import java.util.List;

public interface TestService {

    // 전체조회
    List<TestDto> getUserList();

    // 등록확인
    void testSaveUser(TestDto inputUser);
}
