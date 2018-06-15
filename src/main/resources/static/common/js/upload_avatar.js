var index = parent.layer.getFrameIndex(window.name); //获取窗口索引  

$(function () {
    $(".dialog-close").click(function () {

        parent.layer.close(index);

    });
});

//上傳頭像

$(function () {

    $(".dialog-close").click(function () {
        $('#upload_img').hide();
        $(".jcrop_w").empty();
        $(".pre-1").empty();
        $(".pre-2").empty();
        $('#save_btn').hide();
    });

    upload_img();

    $("#save_btn").click(function () {

        if (!$(".final-img>img")[0]) {
            return false;
        }
        else {
            var img_data = $(".final-img>img")[0].src.split('base64,')[1];

            $.ajax({
                url: '../file/upload_base64',
                type: 'POST',
                dataType: 'json',
                contentType: 'text/html; Charset=utf-8',
                data: img_data,
                async: false,
                cache: false,
                success: function (data) {

                    img_uid = data.fileId;
                    var imgSrc = '../file/download/' + img_uid;

                    parent.$(".img-wrapper>img").attr("src", imgSrc);

                    parent.layer.close(index);

                },
                error: function (err) {
                    alert('error');
                }
            });

        }
    })
});

function file_upload() {
//    	 alert('file_upload');
    $.ajaxFileUpload({
        url: '../file/upload_img',
        secureuri: false,
        fileElementId: 'imgUpload',// file标签的id
        dataType: 'json',// 返回数据的类型

        success: function (data) {   // 读取从后台传来的图像信息并显示

            var src = "../file/download/" + data[0].fileId;
            var dom_img = new Image();
            dom_img.src = src;
            dom_img.style.display = 'none';

            var img_pre = '<img src="' + src + '" />';
            dom_img.onload = function () {
                $(".jcrop_w").empty().append(dom_img);
                $(".pre-1").empty().append(img_pre);
                $(".pre-2").empty().append(img_pre);

                $(".jcrop_w>img").attr('id', 'target');

                // 选中部分居中显示
                var _Jw = ($("#target").width() - 168) / 2,
                    _Jh = ($("#target").height() - 168) / 2,
                    _Jw2 = _Jw + 188,
                    _Jh2 = _Jh + 188;

                $('#target').Jcrop({
                    setSelect: [_Jw, _Jh, _Jw2, _Jh2],
                    minSize: [20, 20],
                    onChange: showPreview,
                    onSelect: showPreview,
                    allowSelect: false,
                    boxWidth: 400,
                    boxHeight: 340,
                    bgFade: true,
                    bgColor: "white",
                    aspectRatio: 1,
                    bgOpacity: .5,

                });
                upload_img();

            }
        },
        error: function (data, status, e) {
            console.log(e);
        }
    });
}

function upload_img() {

    $("#imgUpload").bind("change", function (e) {
        file_upload(); // 上传图像到服务器，取得服务器数据后显示图像
    });
};

function showPreview(c) {
    var iw = $('.jcrop_w>img').width(),
        ih = $('.jcrop_w>img').height(),
        ow = (400 - iw) / 2,
        oh = (340 - ih) / 2,
        rx = 128 / c.w,
        ry = 128 / c.h,
        rx1 = 90 / c.w,
        ry1 = 90 / c.h;

    pre_img2($('.pre-1 img'), rx, iw, ry, ih, c.x, c.y, ow, oh);
    pre_img2($('.pre-2 img'), rx1, iw, ry1, ih, c.x, c.y, ow, oh);

    var canvas = document.getElementById('myCanvas');
    var img = document.getElementById('target');

    var srcX = c.x;
    var srcY = c.y;
    var srcW = c.w;
    var srcH = c.h;

    canvas.width = 200;
    canvas.height = 200;

    if ($('#myCanvas') == null)
        return false;
    var context = canvas.getContext("2d");
    context.drawImage(img, srcX, srcY, srcW, srcH, 0, 0, 200, 200);

    var imgURL = canvas.toDataURL("image/jpg");
    var img = '<img src="' + imgURL + '" />';
    $(".final-img").empty().append(img);
}

function pre_img2(obj, rx, iw, ry, ih, cx, cy, ow, oh) {
    obj.css({
        width: Math.round(rx * iw) + 'px',
        height: Math.round(ry * ih) + 'px'
    });
    if (cy >= oh && cx >= ow) {
        obj.css({
            marginLeft: '-' + Math.round(rx * cx) + 'px',
            marginTop: '-' + Math.round(ry * cy) + 'px'
        });
    } else if (cy <= oh && cx >= ow) {
        obj.css({
            marginLeft: '-' + Math.round(rx * cx) + 'px',
            marginTop: '-' + Math.round(ry * cy) + 'px'
        });
    } else if (cy >= oh && cx <= ow) {
        obj.css({
            marginLeft: '-' + Math.round(rx * cx) + 'px',
            marginTop: '-' + Math.round(ry * cy) + 'px'
        });
    } else if (cy <= oh && cx <= ow) {
        obj.css({
            marginLeft: '-' + Math.round(rx * cx) + 'px',
            marginTop: '-' + Math.round(ry * cy) + 'px'
        });
    }
};

