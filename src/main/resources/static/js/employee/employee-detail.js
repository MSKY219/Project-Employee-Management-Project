$(document).ready(function () {
    const addEmp = $('.addEmp');
    const eId = $('#eId');
    const closeBtn = $('.closeBtn');

    addEmp.on('click', () => {
        console.log('추가 버튼 누름');
        console.log("eId : " + eId.val());
        let openedWindow = window.open('/member/getList/' + eId.val(), 'test', 'width=1400px,height=700px,scrollbars=yes');
    });

    closeBtn.on('click', () => {
        window.history.back();
    });

    // 체크 박스 선택
    const selectAll = $('#selectAll');
    const boxes = $('.boxes');
    const idArr = [];

    selectAll.on('change', (e) => {
        let checked = selectAll.prop('checked');
        console.log(checked);

        boxes.prop('checked', checked); // 모든 체크박스에 대해 상태 변경

        if (checked) {
            // 전체 선택 시 모든 eId 배열에 추가
            idArr.length = 0; // 배열 비우기
            boxes.each(function () {
                let eId = $(this).val();
                idArr.push(eId);
            });
        } else {
            // 전체 해제 시 배열 비우기
            idArr.length = 0;
        }

        console.log("idArr : " + idArr);
    });

    boxes.on('change', (e) => {
        let clickTarget = $(e.currentTarget);
        let eId = clickTarget.val();

        if (clickTarget.prop('checked')) {
            idArr.push(eId);
        } else {
            let index = idArr.indexOf(eId);
            if (index !== -1) {
                idArr.splice(index, 1);
            }
        }

        console.log("idArr : " + idArr);
    });

    const deleteAll = $('.deleteAll');
    deleteAll.on('click', () => {
        console.log("삭제 버튼 누름");
        console.log("pdetail.id : " + JSON.stringify(idArr));
        console.log("user.id : " + eId.val());

        $.ajax({
            url: "/member/delete/project",
            data: {
                "pIdArr": idArr,
                "empId": eId.val()
            },
            type: "post",
            traditional: true,
            success: function (res) {
                console.log("성공");
                window.location.reload();
                alert("삭제 성공");
            },
            error: function () {
                console.log("실패");
            }
        });

    });
});