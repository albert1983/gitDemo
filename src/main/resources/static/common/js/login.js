/**
 * 获取验证码
 */
function replaceVerifyKey() {
    var varifyUrl = "verifyImage?key=" + $("#inp-code").val() + Math.random()
    var verifyKey = varifyUrl.split("=")[1]

    $("#code-pic").attr("src", varifyUrl)
    $("#inp-code").val(verifyKey)
}


/**
 * 是否选中记住密码
 */
function check() {
    $(".member-password").toggleClass("checked");
}
;


/**
 * 登录
 */
function submit() {
    form = document.getElementById("login_form").submit();

    if ($(".member-password").hasClass("checked")) {
        localStorage.setItem($(".login-user").val(), $(".login-pass").val());
    }
}

/**
 * 键盘enter提交登录
 */
$(function () {
    document.onkeydown = function (e) {
        var ev = document.all ? window.event : e;
        if (ev.keyCode == 13) {
            submit();
        }
        ;
    };
});


/**
 * 左上角时间
 */
$(function () {
        Date.prototype.format = function (format) {
            var o = {
                "M+": this.getMonth() + 1, //month
                "d+": this.getDate(), //day
                "h+": this.getHours(), //hour
                "m+": this.getMinutes(), //minute
                "s+": this.getSeconds(), //second
                "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
                "S": this.getMilliseconds() //millisecond
            };
            if (/(y+)/.test(format)) {
                format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            }

            for (var k in o) {
                if (new RegExp("(" + k + ")").test(format)) {
                    format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
                }
            }
            return format;
        };

        setInterval(function () {
            var now = new Date();
            $(".now-date").html(now.format("yyyy年MM月dd日"));
            $(".now-time").html(now.format("hh:mm:ss"));
        });
    }
);


$(function () {
    $("#bg div").not("#bg div:first").fadeOut();
    var num = 0;
    setInterval(function () {
        num++;
        if (num == 3) {
            num = 0;
        }
        $("#bg div").fadeOut(2000);
        $("#bg div").eq(num).fadeIn(2000);
    }, 5000);
});


$(function () {
    $(".login-user").on("input propertychange", function () {
        console.log(localStorage.getItem($(this).val()));
        if (localStorage.getItem($(this).val())) {
            $(".login-pass").val(localStorage.getItem($(this).val()));
            $(".member-password").addClass("checked");
        } else {
            $(".login-pass").val("");
            $(".member-password").removeClass("checked");
        }
    })
})




