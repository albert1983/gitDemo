/**
 * 重置密码
 */

// 打开重置密码框
function reset_password(This) {

    layer.open({
        type: 1,
        title: false,
        closeBtn: 0,
        shade: 0.3,
        area: ["520px", "280px"],
        content: $('.reset')
    });

    show_reset_password(This)

}

function show_reset_password(_this) {
    var index = $(_this).parents('.user-item').index();

    allUsers.userInfo(allUsers.userList()[index]);

    $(".reset-content input").val("").removeClass("error");
    console.log(allUsers.userInfo())
}

// 关闭重置密码框
function close_reset() {
    layer.closeAll();
    $(".reset-content input").val("").removeClass("error");
    $(".reset-content label").hide();
}

jQuery.validator.addMethod("isTowCharacter", function (value, element) {
    var pass = /^(?![\d]+$)(?![a-zA-Z]+$)(?![!@#$%^&*_]+$)[\da-zA-Z!@#$%^&*_]{6,18}$/;
    return this.optional(element) || (pass.test(value));
}, "请输入两种以上字符");
var validate = $("#reset-password").validate(
    {

        rules: {
            password: {
                required: true,
                isPassword: true,
                isTowCharacter: true
            },
            confirm_password: {
                required: true,
                isPassword: true,
                isTowCharacter: true,
                equalTo: ".new-password"
            }
        },
        messages: {
            password: {
                required: "请输入密码",
                isPassword: "请输入6-18位密码"
            },
            confirm_password: {
                required: "请输入密码",
                isPassword: "请输入6-18位密码",
                equalTo: "两次密码输入不一致"
            }
        },
        // focusInvalid:true,//提交表单后，未通过验证的表单（第一个或提交之前获得焦点的未通过验证的 表单）是否会获得焦点
        // focusCleanup : true,//当未通过验证的元素获得焦点时，是否移除错误提示
        submitHandler: function (form) {

            var userId = $("#user_id").val();
            var cellPhone = $("#new_cellphone").text();
            var password = $(".new-password").val();
            var password1 = $(".new-password-confirm").val();

            var json = JSON.stringify({
                "password": password,
                "userId": userId,
                "cellPhone": cellPhone

            });

            console.log(json)

            call_service(contextPath + "/webService/user_resetPassword",
                true, "post", json, function (data) {
                    console.log(data);
                    reset_close();
                }, function (message) {
                    msgTips(message + "通过userId!");
                });

        }

    });

// 修改密码成功后显示的框
function reset_close() {
    layer.closeAll();

    layer.open({
        type: 1,
        title: false,
        closeBtn: 0,
        shade: 0.3,
        area: '340px',
        content: $('.reset-success')
    });

    setTimeout(function () {
        layer.closeAll();
    }, 1000);
}


// 禁用联系人
var marked_word;
var marked_msg;

function disable_btn(_this) {
    marked_word = "确定要禁用吗?";
    showDialog_tip(_this);
    marked_msg = "禁用成功";

}

// 启用联系人
function enable_btn(_this) {
    marked_word = "确定要启用吗?";
    showDialog_tip(_this);
    marked_msg = "启用成功";

}

// 启禁用联系人
var forbid_member_index;

function showDialog_tip(_this) {

    msgBox(marked_word
        // 按钮
        , function () {

            var index = $(_this).parents('.user-item').index();
            allUsers.userInfo(allUsers.userList()[index]);

            var _userId = $(_this).attr("uId");
            var _userStatus = $(_this).attr("ustatus");
            var _orgId = ""

            if (u_status == 'search_user') {
                var param = "userId=" + _userId;
                call_service(contextPath + '/webService/org_getOrgByUserId', false,
                    "get", param, function (data) {
                        console.log("dddddddd")
                        console.log(data)
                        _orgId = data.orgId
                    }, function (message) {
                        msgTips(message + "通过userId获取组织异常!");
                    });

            } else {
                _orgId = allUsers.userInfo().orgId

            }

            var json = JSON.stringify({
                "userId": _userId,
                "userStatus": _userStatus,
                "orgId": _orgId
            });

            console.log(json)
            // 利用对话框返回的值 （true 或者 false）
            call_service(contextPath + "/webService/user_enOrDisUserByProUser",
                true,

                "post", json, function (data) {

                    getUserByOrgId(_orgId)

                }, function (message) {
                    msgTips(message + "禁用或启用用户异常!");
                });

            msgTips(marked_msg);
        });

}


// 清空搜索,默认选中应用
function clear_search() {
    $("#search_name").val("");
    $("#search_cell_phone").val("");
    $("#search_status span").removeClass('active');
    $("#valid").addClass('active');
}

// 搜索
function on_search(_this) {
    u_status = $(_this).attr("id");// search_user
    //获取根节点
    //var node = $selectableTree.treeview('getNode', 0);
    // 设置默认选中
    $selectableTree.treeview('selectNode', [0, {
        silent: true
    }]);

    var name = $("#search_name").val();
    var cellPhone = $("#search_cell_phone").val();
    var status = $("#search_status .active").attr("value");
    var parm = "name=" + name + "&cellPhone=" + cellPhone + "&status=" + status;

    $("#prenext").ajaxpagemodel({// 通过proId获取User
        api_url: contextPath + '/webService/user_findUsersByParamsPage', // 必填，数据调用地址
        args: parm,// 选填，调用条件
        nowpage: 1,// 选填，当前页，默认为1
        pagesize: 15,// 选填，每页显示条数，默认为10
        onChecked: gettablelist
        // 返回数据列表 gettablelist的function 需要自己写,初始化的时候就会执行一次
    });
}
