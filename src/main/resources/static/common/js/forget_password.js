// 验证码计时器
function timing() {
    var num = 60;
    var time = setInterval(function () {
        num--;
        if (num < 10) {
            $('.get-code span').html("0" + num);
        } else {
            $('.get-code span').html(num);
        }
        if (num == 0) {
            $(".get-code").removeClass("disabled").attr('disabled', false);
            $('.get-code span').html(60);
            clearInterval(time);
        }
    }, 1000)
}

//var model = {
//    code: ko.observable("000000")
//}

$(function () {

    //ko.applyBindings(model, $("#phone_code")[0]);


    // 验证手机号是否存在
    $.validator.addMethod("isExistCellPhone", function (value) {
        var result = false;
        $.ajax({
            url: "../user/checkIsOnlyPhone",// 后台处理程序
            type: "post",// 数据发送方式
            dataType: "json",// 接受数据格式
            async: false,
            data: {
                "cellPhone": value
            },// 要传递的数据
            success: function (data) {
                result = data;//true or false
            },
        });
        return result;
    }, "手机号不存在!");

    // 点击获取验证码
    $(".get-code").click(
        function () {
            var cellPhone = $("#phone").text();
            var json = JSON.stringify({
                "cellPhone": cellPhone
            });
            console.log(json)
            $.ajax({
                url: "../user/getPhoneVerificationCodeByPhone",// 后台处理程序
                type: "post",// 数据发送方式
                dataType: "json",// 接受数据格式
                async: true,
                data: {
                    "cellPhone": cellPhone
                },// 要传递的数据
                success: function (data) {
                    if (data.resultCode === "0") {
                        timing();
                    } else if (data.resultCode === "9") {
                        msgBox("一小时内验证码超过三次,请稍后重试");
                    } else if (data.resultCode === "2") {
                        msgBox("60秒内仅能获取一次验证码，请稍后重试");
                        timing();
                    } else if (data.resultCode == "1" || data.resultCode == "3") {
                        msgTips("网络异常，请重新获取！");
                    }
                },
                error: function (message) {
                    msgTips(message + "获取验证码失败!");
                },
                complete: function () {
                    console.log('结束')
                }
            });
            $(".get-code").addClass("disabled").attr('disabled', true);
        });


    $("#phone-verify").focus(function () {
        $("#verify-error-new").html("");
    })
// 重置密码第一步
    $("#forget_password_01").validate({
        focusCleanup: true,//获取焦点时移除错误提示
        focusInvalid: false,//错误时是否自动获得焦点
        rules: {
            verifyCode: {
                required: true,
                rangelength: [4, 4]
            }
        },
        messages: {
            verifyCode: {
                required: "请输入验证码!",
                rangelength: "请输入四位验证码!"
            }
        },
        submitHandler: function (form) {

            var verifyCode = $("#verify").val();
            var verifyKey = $("#inp-code").val();

            var json = JSON.stringify(
                {verifyCode: verifyCode, verifyKey: verifyKey}
            )
            call_service(contextPath + "/webService/user_verifyCode", false,
                "post", json, function (data) {
                    if (data.resultCode === "1") {
                        msgTips("验证码超时,请重新获取")
                    } else if (data.resultCode === "2") {
                        msgTips("验证码错误!");
                    } else if (data.resultCode === "0") {
                        form.submit();
                    }
                }, function (messsage) {
                    msgTips("请求失败!");
                }
            )
        }
    })
// 重置密码第二步
    $("#forget_password_02").validate({

        focusCleanup: true,//获取焦点时移除错误提示
        focusInvalid: false,//错误时是否自动获得焦点
        onkeyup: false,
        rules: {
            phone_verify: {
                required: true,
                rangelength: [6, 6]
            }
        },
        messages: {
            phone_verify: {
                required: "请输入手机验证码!",
                rangelength: "请输入6位手机验证码!"
            }
        },
        submitHandler: function (form) {

            var cellPhone = $("#phone").text();
            var verifyCode = $("#phone-verify").val();
            var json = JSON.stringify(
                {verifyCode: verifyCode, cellPhone: cellPhone}
            )
            call_service(contextPath + "/webService/user_validateVerify", false,
                "post", json, function (data) {
                    if (data.resultCode === "1") {
                        msgTips("验证码超时")
                    } else if (data.resultCode === "2") {
                        msgTips("验证码错误!");
                    } else if (data.resultCode === "0") {
                        form.submit();
                    }
                }, function (messsage) {
                    msgTips("请求失败!");
                }
            )
        }
    })
    jQuery.validator.addMethod("isTowCharacter", function (value, element) {
        var pass = /^(?![\d]+$)(?![a-zA-Z]+$)(?![!@#$%^&*_]+$)[\da-zA-Z!@#$%^&*_]{6,18}$/;
        return this.optional(element) || (pass.test(value));
    }, "请输入两种以上字符组合的密码");
// 重置密码第三步
    $("#forget_password_03").validate(
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
                    equalTo: "#password",
                    isTowCharacter: true
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
            submitHandler: function (form) {
                var cellPhone = $("#phone").val();
                var comfirm_password = $("#confirm-password").val();
                var json = JSON.stringify({
                    "password": comfirm_password,
                    "cellPhone": cellPhone
                });
                console.log(json);
                call_service(contextPath
                    + "/webService/user_forgetModifyPassword", true,
                    "post", json, function (data) {
                        console.log(data);
                        form.submit();
                    }, function (message) {
                        msgTips(message + "通过userId!");
                    });

            }

        });

})
;
