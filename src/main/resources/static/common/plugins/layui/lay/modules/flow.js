/** layui-v1.0.4 LGPL license By www.layui.com */
;layui.define("jquery", function (o) {
    "use strict";
    var e = layui.jquery, l = function (o) {
    }, i = '<i class="layui-anim layui-anim-rotate layui-anim-loop layui-icon ">&#xe63e;</i>';
    l.prototype.load = function (o) {
        var l, t, n, r, a = this, c = 1;
        o = o || {};
        var u = e(o.elem);
        if (u[0]) {
            var f = e(o.scrollElem || document), m = o.mb || 50, s = !("isAuto" in o) || o.isAuto,
                y = !("isShowEnd" in o) || o.isShowEnd, h = o.scrollElem && o.scrollElem !== document,
                v = "<cite>加载更多</cite>",
                d = e('<div class="layui-flow-more"><a href="javascript:;">' + v + "</a></div>");
            u.find(".layui-flow-more")[0] || u.append(d);
            var p = function (o, i) {
                o = e(o), d.before(o), i = 0 == i || null, i ? d.html(y ? "没有更多了" : "") : d.find("a").html(v), t = i, l = null, n && n()
            }, g = function () {
                l = !0, d.find("a").html(i), "function" == typeof o.done && o.done(++c, p)
            };
            if (d.find("a").on("click", function () {
                    e(this);
                    t || l || g()
                }), o.isLazyimg) var n = a.lazyimg({elem: o.elem + " img", scrollElem: o.scrollElem});
            return s ? (f.on("scroll", function () {
                var o = e(this), i = o.scrollTop();
                r && clearTimeout(r), t || (r = setTimeout(function () {
                    var t = h ? o.height() : e(window).height(),
                        n = h ? o.prop("scrollHeight") : document.documentElement.scrollHeight;
                    n - i - t <= m && (l || g())
                }, 100))
            }), a) : a
        }
    }, l.prototype.lazyimg = function (o) {
        var l, i = this, t = 0;
        o = o || {};
        var n = e(o.scrollElem || document), r = o.elem || "img", a = o.scrollElem && o.scrollElem !== document,
            c = function (o, e) {
                var l = n.scrollTop(), r = l + e, c = a ? function () {
                    return o.offset().top - n.offset().top + l
                }() : o.offset().top;
                if (c >= l && c <= r && !o.attr("src")) {
                    var f = o.attr("lay-src");
                    layui.img(f, function () {
                        var e = i.lazyimg.elem.eq(t);
                        o.attr("src", f).removeAttr("lay-src"), e[0] && u(e), t++
                    })
                }
            }, u = function (o, l) {
                var u = a ? (l || n).height() : e(window).height(), f = n.scrollTop(), m = f + u;
                if (i.lazyimg.elem = e(r), o) c(o, u); else for (var s = 0; s < i.lazyimg.elem.length; s++) {
                    var y = i.lazyimg.elem.eq(s), h = a ? function () {
                        return y.offset().top - n.offset().top + f
                    }() : y.offset().top;
                    if (c(y, u), t = s, h > m) break
                }
            };
        if (u(), !l) {
            var f;
            n.on("scroll", function () {
                var o = e(this);
                f && clearTimeout(f), f = setTimeout(function () {
                    u(null, o)
                }, 100)
            }), l = !0
        }
        return u
    }, o("flow", new l)
});