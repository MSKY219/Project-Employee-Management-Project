$(document).ready(function () {
    const empname = $('#name');
    const addrMain = $('#sample4_roadAddress');
    const addrDetail = $('#sample4_detailAddress');
    const tel = $('#phone');
    const email = $('#email');
    const img = $('.empImg');
    const enterAt = $('#date');
    const gender = $('#gender');
    const jobtitle = $('#job-title');
    const dept = $('#department');
    const skill = $('#tech');
    const grade = $('#grade');
    const education = $('#edu');
    const regNo = $('#reg-no');

    const checkObj = {
        "empName": false,
        "empTel": false,
        "regNo": false,
        "gender": false,
        "email": false
    };

    // 이름 유효성 검사
    const nameMessage = $('#nameMessage');
    empname.on('input change keyup paste', () => {

        // 입력되지 않은 경우
        if (empname.val().length == 0) {
            console.log("이름값 0");
            nameMessage.text("한글 2~10글자 사이로 작성해주세요.");
            nameMessage.removeClass("confirm").addClass("error");
            checkObj.empName = false;
            return;
        };

        // 이름 확인 정규식
        const regExp = /^[가-힣]{2,10}$/;

        // 이름 입력 시,
        if (regExp.test($('#name').val())) {
            console.log("이름 정규식 통과. empname : " + empname.val());
            nameMessage.text("유효한 이름입니다.");
            nameMessage.removeClass("error").addClass("confirm");
            checkObj.empName = true;
        } else {
            nameMessage.text("한글 2~10글자 사이로 작성해주세요.");
            nameMessage.removeClass("confirm").addClass("error");
            checkObj.empName = false;
        };
    });

    // 연락처 유효성 검사
    const telMessage = $('#telMessage');
    tel.on('input change keyup paste', (e) => {

        e.preventDefault();

        let input = e.target.value.split("-").join("");

        input = input.split('').map(function (cur, index) {

            if (index == 3 || index == 7) {
                return "-" + cur;
            } else {
                return cur;
            };

        }).join('');


        tel.val(input);

        // 입력되지 않은 경우
        if (tel.val().length == 0) {
            telMessage.text("숫자만 입력해주세요.");
            telMessage.removeClass("confirm").addClass("error");
            checkObj.empTel = false;
            return;
        };

        // 연락처 확인 정규식
        const regExp = /\d{3}-\d{4}-\d{4}/g;


        // 연락처 입력 시,
        if (regExp.test($('#phone').val())) {
            console.log("번호 정규식 통과. tel : " + tel.val());
            telMessage.text("유효한 번호입니다.");
            telMessage.removeClass("error").addClass("confirm");
            checkObj.empTel = true;
        } else {
            telMessage.text("휴대전화 번호만 입력해주세요.");
            telMessage.removeClass("confirm").addClass("error");
            checkObj.empTel = false;
        };
    });

    // 주민번호 유효성 검사
    const regNoMessage = $('#regNoMessage');
    regNo.on('input', (e) => {

        e.preventDefault();

        let input = e.target.value.split("-").join("");

        input = input.split('').map(function (cur, index) {

            if (index == 6)
                return "-" + cur;
            else
                return cur;
        }).join('');


        regNo.val(input);

        // 입력되지 않은 경우
        if (regNo.val().length == 0) {
            regNoMessage.text("유효하지 않는 형식입니다.");
            regNoMessage.removeClass("confirm").addClass("error");
            checkObj.regNo = false;
            return
        };

        // 주민번호 확인 정규식
        const regExp = /^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))-[1-4][0-9]{6}$/g;

        // 주민번호 입력 시,
        if (regExp.test($('#reg-no').val())) {
            regNoMessage.text("유효한 주민번호 입니다.");
            regNoMessage.removeClass("error").addClass("confirm");
            checkObj.regNo = true;
        } else {
            regNoMessage.text("형식이 올바르지 않습니다.");
            regNoMessage.removeClass("confirm").addClass("error");
            checkObj.regNo = false;
        };
    });

    // 이메일 유효성 검사
    const emailMessage = $('#emailMessage');
    email.on('input', $.debounce(1000, () => {
        // 입력되지 않은 경우
        if (email.val().length == 0) {
            emailMessage.text("이메일을 입력해 주세요.");
            emailMessage.removeClass("confirm").addClass("error");
            checkObj.email = false;
            return
        };

        // 이메일 정규식
        const regExp = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+){1,3}$/;

        // 이메일 입력 시,
        if (regExp.test($('#email').val())) {

            $.ajax({
                url: "/member/checkEmail",
                type: "get",
                data: {
                    "email": email.val()
                },
                success: function (res) {
                    if (res >= 1) { // 중복 O
                        emailMessage.text("사용중인 이메일입니다.");
                        emailMessage.removeClass("confirm").addClass("error");
                        checkObj.email = false;
                        return
                    } else { // 중복 X
                        emailMessage.text("사용 가능한 이메일입니다.");
                        emailMessage.removeClass("error").addClass("confirm");
                        checkObj.email = true;
                    }
                },
                error: function () {

                }
            });



        } else {
            emailMessage.text("이메일 올바르지 않습니다.");
            emailMessage.removeClass("confirm").addClass("error");
            checkObj.email = false;
        }
    }));

    // 성별 유효성 검사
    gender.on('change', () => {
        if ($('#gender').val() == '0') {
            checkObj.gender = false;
            console.log(checkObj.gender);
        } else {
            checkObj.gender = true;
            console.log(checkObj.gender);
        }
    });



    // 입사일 검증기능
    let getCurrentDate = new Date();
    let currentDate = getCurrentDate.getFullYear() + "-" + ('0' + (getCurrentDate.getMonth() + 1)).slice(-2) + "-" + getCurrentDate.getDate();

    enterAt.on('blur', () => {
        let checking = enterAt.val();

        if (!checking.trim()) { // trim()을 사용하여 값의 앞뒤 공백을 제거하고 빈 문자열 여부를 확인
            alert('날짜를 확인해 주세요.');
            enterAt.val("");
            return false;
        }

        console.log(checking);
    });

    // 취소 버튼 눌렀을 시, 뒤로가기.
    const goBackBtn = $('.secondary');
    goBackBtn.on('click', () => {
        window.history.back();
    });

    // 이미지 등록용
    img.on('change', (e) => {

        console.log(e.target.files);
        console.log("img : " + img.val());

        for (var i = 0; i < e.target.files.length; i++) {

            if (!checkExtension(e.target.files[i].name, e.target.files[i].size)) {
                img.val('');
                return false;
            }

            uploadImageFile(e.target.files[i]); // 파일 전달
        }
    });


    let imageUrl;

    function uploadImageFile(file) {
        var data = new FormData();
        data.append("file", file);
        $.ajax({
            url: '/member/saveImg',
            type: 'POST',
            data: data,
            cache: false,
            contentType: false, // 서버로 전송되는 content-type
            processData: false, // 서버로 전송할 데이터를 queryString으로 변환 여부
            dataType: "json",
            success: function (data1) {
                // console.log("성공 후 반환 메시지11", data1);
                // let jsonArray = JSON.parse(data1); // JSON 문자열을 파싱하여 배열로 변환
                // let imageObject = jsonArray[0]; // 배열의 첫 번째 요소 선택
                let imageObject = data1[0];
                imageUrl = imageObject[""]; // 빈 키에 해당하는 이미지 URL 선택
                console.log("이미지 URL:", imageUrl);
            },
            error: function (e) {
                // console.log(e);
            }
        });
    };

    const profileImage = document.querySelector('#profile-image');
    const profileUpload = document.querySelector('#profile-upload');

    profileUpload.addEventListener('change', (e) => {
        const file = e.target.files[0];
        if (file) profileImage.src = URL.createObjectURL(file);
    });

    let regex = new RegExp("(.*?\.(png|jpg|gif|jpeg)$)");
    let maxSize = 5000000; // 5MB 제한

    function checkExtension(fileName, fileSize) {
        if (fileSize >= maxSize) {
            alert("파일 사이즈 초과");
            return false;
        }
        if (!regex.test(fileName)) {
            alert("해당 종류 파일은 업로드 안됨.\n PNG, JPG, GIF, JPEG 만 가능합니다.");
            return false;
        }
        return true;
    };

    // 이미지 등록용 END

    const savekBtn = $('.addEmp');
    savekBtn.on('click', (e) => {
        if (checkObj.empName == false) {
            alert('이름을 입력 하지 않았습니다.');
            empname.focus();
            e.preventDefault();
            return false;
        };

        if (checkObj.empTel == false) {
            alert('전화번호를 입력 하지 않았습니다.');
            tel.focus();
            e.preventDefault();
            return false;
        };

        if (checkObj.regNo == false) {
            alert('주민번호를 입력 하지 않았습니다.');
            regNo.focus();
            e.preventDefault();
            return false;
        };

        if (checkObj.gender == false) {
            alert('성별을 선택 하지 않았습니다.');
            gender.focus();
            e.preventDefault();
            return false;
        };

        if (checkObj.email == false) {
            alert('이메일을 입력 하지 않았습니다.');
            email.focus();
            e.preventDefault();
            return false;
        };

        // 직원 등록 ajax
        $.ajax({
            url: "/member/save",
            data: {
                "empName": empname.val(),
                "addrMain": addrMain.val(),
                "addrDetail": addrDetail.val(),
                "tel": tel.val(),
                "email": email.val(),
                "img": imageUrl,
                "enterAt": enterAt.val(),
                "gender": gender.val(),
                "jobTitle": jobtitle.val(),
                "dept": dept.val(),
                "skill": skill.val(),
                "grade": grade.val(),
                "education": education.val(),
                "regNo": regNo.val()
            },
            type: "post",

            success: function (res) {
                console.log("성공");
                alert("직원 등록 성공");
                location.replace("/member");
            },
            error: function () {
                console.log("실패");
            }
        });
    });
});

/* 카카오 주소 API */
function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function (data) {

            var roadAddr = data.roadAddress; // 도로명 주소 변수

            document.getElementById("sample4_roadAddress").value = roadAddr;

            document.getElementById("sample4_detailAddress").readOnly = false;
        }
    }).open();
};