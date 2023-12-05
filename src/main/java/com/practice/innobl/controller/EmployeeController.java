package com.practice.innobl.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.practice.innobl.common.Util;
import com.practice.innobl.dto.commoncode.CommondefinitionDto;
import com.practice.innobl.dto.emp.EmailCheckRequestDto;
import com.practice.innobl.dto.emp.EmpRequestDto;
import com.practice.innobl.dto.emp.EmpResponseDto;
import com.practice.innobl.dto.emp.SearchRequest;
import com.practice.innobl.service.emp.EmpService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

    private final EmpService empService;

    // 직원 목록 창 이동
    @GetMapping("/member")
    public String userList(Model model,
                           @RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
                           @RequestParam(value = "status", required = false, defaultValue = "") String status,
                           @RequestParam(value = "skill", required = false, defaultValue = "") String skill,
                           @RequestParam(value = "grade", required = false, defaultValue = "") String grade,
                           @RequestParam(value = "dateStart", required = false, defaultValue = "") String dateStart,
                           @RequestParam(value = "dateEnd", required = false, defaultValue = "") String dateEnd,
                           @RequestParam(value = "others", required = false, defaultValue = "") String others,
                           @RequestParam(value = "othersDetail", required = false, defaultValue = "") String othersDetail
    ) throws IllegalAccessException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setStatus(status);
        searchRequest.setSkill(skill);
        searchRequest.setGrade(grade);
        searchRequest.setDateStart(dateStart);
        searchRequest.setDateEnd(dateEnd);
        searchRequest.setOthers(others);
        searchRequest.setOthersDetail(othersDetail);

        String query = searchRequest.toQueryString();

        System.out.println(searchRequest);

        boolean isSearch = !searchRequest.isEmpty();

            List<CommondefinitionDto> getAddOption = empService.getAddOptions();
        if(isSearch) {
            Map<String, Object> getEmpList = empService.getEmpList(cp, searchRequest);
            model.addAttribute("empList", getEmpList.get("getEmpList"));
            model.addAttribute("pagination", getEmpList.get("pagination"));
        }
        model.addAttribute("optionList", getAddOption);
        model.addAttribute("activeMenu", "memberList");
        model.addAttribute("searchRequest", searchRequest);
        model.addAttribute("query", query);
        return "employee-list";
    }

    // 직원 목록 -> 직원 상태 변경
    @PostMapping("/member/update/st")
    public @ResponseBody String updateSt(EmpRequestDto empRequestDto) {

        int result = empService.updateSt(empRequestDto);

        return null;
    }

    // 직원 등록창 이동
    @GetMapping("/member/add")
    public String addUser(Model model) {

    // 등록 창 이동시 필요 정보 가지고 오기
    List<CommondefinitionDto> getAddOption = empService.getAddOptions();
        model.addAttribute("optionList", getAddOption);
        model.addAttribute("activeMenu", "addMember");
        return "employee-add";
    }

    // 직원 등록
    @PostMapping("/member/save")
    public @ResponseBody String saveEmp(@ModelAttribute EmpRequestDto empRequestDto) {
         int result = empService.saveEmp(empRequestDto);
        return "employee-list";
    }

    // 직원 등록 및 수정 시, 이메일 중복 확인
    @GetMapping("/member/checkEmail")
    public @ResponseBody int checkEmail(@ModelAttribute EmailCheckRequestDto emailCheck) {
        int result = empService.checkEmail(emailCheck);
        return result;
    }

    // 직원 이미지 등록 및 수정
    @PostMapping("/member/saveImg")
    @ResponseBody
    public String movieUploadImageFile(@RequestParam("file") MultipartFile[] multipartFiles, HttpServletRequest request) {
        JsonArray jsonArray = new JsonArray();

        String webPath = "/images/";
        String fileRoot = request.getServletContext().getRealPath(webPath);

        for (MultipartFile multipartFile : multipartFiles) {
            JsonObject jsonObject = new JsonObject();

            String originalFileName = multipartFile.getOriginalFilename();
            String savedFileName = Util.fileRename(originalFileName);

            File targetFile = new File(fileRoot + savedFileName);
            try {
                InputStream fileStream = multipartFile.getInputStream();
                FileUtils.copyInputStreamToFile(fileStream, targetFile);
                jsonObject.addProperty("", request.getContextPath() + webPath + savedFileName);

            } catch (IOException e) {
                FileUtils.deleteQuietly(targetFile);
                jsonObject.addProperty("responseCode", "error");
                e.printStackTrace();
            }

            jsonArray.add(jsonObject);
        }

        String jsonResult = jsonArray.toString();
        System.out.println("이미지: " + jsonResult);
        System.out.println("fileRoot : " + fileRoot);
        return jsonResult;
    }

    // 직원 상세내역 조회
    @GetMapping("/member/detail/{id}")
    public String empDetail(Model model, @PathVariable Long id) {
        List<EmpResponseDto> getEmpDetail = empService.empDetail(id);
        model.addAttribute("empDetail", getEmpDetail);
        model.addAttribute("activeMenu", "memberList");
        return "employee-detail";
    }

    // 직원 정보 조회 (수정 하기 전 조회)
    @GetMapping("/member/edit/{id}")
    public String getEmpEditableInfo(Model model, @PathVariable Long id) {
        List<EmpResponseDto> getEmpDetail = empService.empDetail(id);
        List<CommondefinitionDto> getAddOption = empService.getAddOptions();
        model.addAttribute("empDetail", getEmpDetail);
        model.addAttribute("optionList", getAddOption);
        model.addAttribute("activeMenu", "memberList");
        return "employee-edit";
    }

    // 직원 정보 수정
    @PostMapping("/member/edit")
    public @ResponseBody String updateEmp(@ModelAttribute EmpRequestDto empRequestDto) {
        int result = empService.updateEmp(empRequestDto);
        return "employee-list";
    }
}
