/**
 used in header
 */

var workspace_data = [
    {workspace_name: "任彦辉的空间"}, {workspace_name: "杨明明的空间"}, {workspace_name: "杨崇元的空间"}
]
var workspace_model = {
    workspace_list: ko.observableArray(workspace_data)
};

$(function () {
    /*$(".dropdown-wrap").find(".btn").click(function(){
     $(this).html();
     })*/
    //console.log(workspace_model)
    ko.applyBindings(workspace_model, $("#workspace_list_container")[0]);


    //增加验证方式
    $.validator.addMethod("isMobile", function (value, element) {
        var length = value.length;
        var mobile = /^1[34578][0-9]\d{8}$/;
        return this.optional(element) || (length == 11 && mobile.test(value));
    }, "请填写正确的手机号码");

    $.validator.addMethod("isPassword", function (value, element) {
        var password = /^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~\_]{6,22}$/;
        return this.optional(element) || ( password.test(value));
    }, "请输入6---22位密码");


    $.validator.addMethod("isPhone", function (value, element) {
        //var phone = /^((\d{3,4})-)?\d{6,8}(-\d{3,5})?$/;
        var phone = /^((0\d{2,3})-)?(\d{6,8})(-(\d{3,5}))?$/;
        return this.optional(element) || ( phone.test(value));
    }, "请填写正确的电话号码");

    $.validator.addMethod("isEmail", function (value, element) {
        var email = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        return this.optional(element) || ( email.test(value));
    }, "请填写正确的邮箱");


})

function msgBox(message, ok_function, cancel_function, success_function) {
    layer.open({
        extend: 'myskin/style.css',
        skin: 'layer-ext-myskin',
        type: 0,
        title: false,
        content: message,
        closeBtn: 0,
        btn: ['确定', '取消'],
        yes: function (index, layero) {
            //yes - 确定按钮回调方法
            if (ok_function) {
                ok_function();
            }
            ;
            layer.close(index);
        },
        cancel: function () {

        },
        success: function (layero, index) {
            // 层成功弹出后的回调方法
            if (success_function) {
                success_function();
            }
        },

    });
}

function messageBox(message, ok_function, clean_function) {
    layer.open({
        extend: 'myskin/style.css',
        skin: 'layer-ext-myskin',
        type: 0,
        title: false,
        content: message,
        closeBtn: 0,
        btn: ['确定', '取消'],
        yes: function (index, layero) {
            ok_function();
        },
        btn2: function (index) {
            clean_function();
        },


    });
}

function msgTips(message) {
    //layer.msg(message,{time:600000});
    layer.msg(message, {time: 1000});
}

layui.use('layer', function () {
    layer = layui.layer;
});
