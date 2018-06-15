/** layui-v1.0.4 LGPL license By www.layui.com */
;layui.define(function (e) {
    "use strict";
    var t = window, a = {
            path: "",
            skin: "default",
            format: "YYYY-MM-DD",
            min: "1900-01-01 00:00:00",
            max: "2099-12-31 23:59:59",
            isv: !1,
            init: !0
        }, n = {}, s = document, i = "createElement", o = "getElementById", l = "getElementsByTagName",
        d = ["laydate_box", "laydate_void", "laydate_click", "LayDateSkin", "skins/", "/laydate.css"];
    t.laydate = function (e) {
        return e = e || {}, n.run(e), laydate
    }, laydate.v = "1.1", n.trim = function (e) {
        return e = e || "", e.replace(/^\s|\s$/g, "").replace(/\s+/g, " ")
    }, n.digit = function (e) {
        return e < 10 ? "0" + (0 | e) : e
    }, n.stopmp = function (e) {
        return e = e || t.event, e.stopPropagation ? e.stopPropagation() : e.cancelBubble = !0, this
    }, n.each = function (e, t) {
        for (var a = 0, n = e.length; a < n && t(a, e[a]) !== !1; a++) ;
    }, n.hasClass = function (e, t) {
        return e = e || {}, new RegExp("\\b" + t + "\\b").test(e.className)
    }, n.addClass = function (e, t) {
        return e = e || {}, n.hasClass(e, t) || (e.className += " " + t), e.className = n.trim(e.className), this
    }, n.removeClass = function (e, t) {
        if (e = e || {}, n.hasClass(e, t)) {
            var a = new RegExp("\\b" + t + "\\b");
            e.className = e.className.replace(a, "")
        }
        return this
    }, n.removeCssAttr = function (e, t) {
        var a = e.style;
        a.removeProperty ? a.removeProperty(t) : a.removeAttribute(t)
    }, n.shde = function (e, t) {
        e.style.display = t ? "none" : "block"
    }, n.query = function (e) {
        if (e && 1 === e.nodeType) {
            if ("input" !== e.tagName.toLowerCase()) throw new Error("选择器elem错误");
            return e
        }
        var t, e = n.trim(e).split(" "), a = s[o](e[0].substr(1));
        if (a) {
            if (e[1]) {
                if (/^\./.test(e[1])) {
                    var i, d = e[1].substr(1), r = new RegExp("\\b" + d + "\\b");
                    return t = [], i = s.getElementsByClassName ? a.getElementsByClassName(d) : a[l]("*"), n.each(i, function (e, a) {
                        r.test(a.className) && t.push(a)
                    }), t[0] ? t : ""
                }
                return t = a[l](e[1]), t[0] ? a[l](e[1]) : ""
            }
            return a
        }
    }, n.on = function (e, a, s) {
        return e.attachEvent ? e.attachEvent("on" + a, function () {
            s.call(e, t.even)
        }) : e.addEventListener(a, s, !1), n
    }, n.stopMosup = function (e, t) {
        "mouseup" !== e && n.on(t, "mouseup", function (e) {
            n.stopmp(e)
        })
    }, n.run = function (e) {
        var t = (n.query, e.elem);
        t && (d.elemv = /textarea|input/.test(t.tagName.toLocaleLowerCase()) ? "value" : "innerHTML", ("init" in e ? e.init : a.init) && !t[d.elemv] && (t[d.elemv] = laydate.now(null, e.format || a.format)), n.view(t, e), n.reshow())
    }, n.scroll = function (e) {
        return e = e ? "scrollLeft" : "scrollTop", s.body[e] | s.documentElement[e]
    }, n.winarea = function (e) {
        return document.documentElement[e ? "clientWidth" : "clientHeight"]
    }, n.isleap = function (e) {
        return e % 4 === 0 && e % 100 !== 0 || e % 400 === 0
    }, n.checkVoid = function (e, t, a) {
        var s = [];
        return e = 0 | e, t = 0 | t, a = 0 | a, e < n.mins[0] ? s = ["y"] : e > n.maxs[0] ? s = ["y", 1] : e >= n.mins[0] && e <= n.maxs[0] && (e == n.mins[0] && (t < n.mins[1] ? s = ["m"] : t == n.mins[1] && a < n.mins[2] && (s = ["d"])), e == n.maxs[0] && (t > n.maxs[1] ? s = ["m", 1] : t == n.maxs[1] && a > n.maxs[2] && (s = ["d", 1]))), s
    }, n.timeVoid = function (e, t) {
        if (n.ymd[1] + 1 == n.mins[1] && n.ymd[2] == n.mins[2]) {
            if (0 === t && e < n.mins[3]) return 1;
            if (1 === t && e < n.mins[4]) return 1;
            if (2 === t && e < n.mins[5]) return 1
        } else if (n.ymd[1] + 1 == n.maxs[1] && n.ymd[2] == n.maxs[2]) {
            if (0 === t && e > n.maxs[3]) return 1;
            if (1 === t && e > n.maxs[4]) return 1;
            if (2 === t && e > n.maxs[5]) return 1
        }
        if (e > (t ? 59 : 23)) return 1
    }, n.check = function () {
        var e = n.options.format.replace(/YYYY|MM|DD|hh|mm|ss/g, "\\d+\\").replace(/\\$/g, ""), t = new RegExp(e),
            a = n.elem[d.elemv], s = a.match(/\d+/g) || [], i = n.checkVoid(s[0], s[1], s[2]);
        if ("" !== a.replace(/\s/g, "")) {
            if (!t.test(a)) return n.elem[d.elemv] = "", n.msg("日期不符合格式，请重新选择。"), 1;
            if (i[0]) return n.elem[d.elemv] = "", n.msg("日期不在有效期内，请重新选择。"), 1;
            i.value = n.elem[d.elemv].match(t).join(), s = i.value.match(/\d+/g), s[1] < 1 ? (s[1] = 1, i.auto = 1) : s[1] > 12 ? (s[1] = 12, i.auto = 1) : s[1].length < 2 && (i.auto = 1), s[2] < 1 ? (s[2] = 1, i.auto = 1) : s[2] > n.months[(0 | s[1]) - 1] ? (s[2] = 31, i.auto = 1) : s[2].length < 2 && (i.auto = 1), s.length > 3 && (n.timeVoid(s[3], 0) && (i.auto = 1), n.timeVoid(s[4], 1) && (i.auto = 1), n.timeVoid(s[5], 2) && (i.auto = 1)), i.auto ? n.creation([s[0], 0 | s[1], 0 | s[2]], 1) : i.value !== n.elem[d.elemv] && (n.elem[d.elemv] = i.value)
        }
    }, n.months = [31, null, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31], n.viewDate = function (e, t, a) {
        var s = (n.query, {}), i = new Date;
        e < (0 | n.mins[0]) && (e = 0 | n.mins[0]), e > (0 | n.maxs[0]) && (e = 0 | n.maxs[0]), i.setFullYear(e, t, a), s.ymd = [i.getFullYear(), i.getMonth(), i.getDate()], n.months[1] = n.isleap(s.ymd[0]) ? 29 : 28, i.setFullYear(s.ymd[0], s.ymd[1], 1), s.FDay = i.getDay(), s.PDay = n.months[0 === t ? 11 : t - 1] - s.FDay + 1, s.NDay = 1, n.each(d.tds, function (e, t) {
            var a, i = s.ymd[0], o = s.ymd[1] + 1;
            t.className = "", e < s.FDay ? (t.innerHTML = a = e + s.PDay, n.addClass(t, "laydate_nothis"), 1 === o && (i -= 1), o = 1 === o ? 12 : o - 1) : e >= s.FDay && e < s.FDay + n.months[s.ymd[1]] ? (t.innerHTML = a = e - s.FDay + 1, e - s.FDay + 1 === s.ymd[2] && (n.addClass(t, d[2]), s.thisDay = t)) : (t.innerHTML = a = s.NDay++, n.addClass(t, "laydate_nothis"), 12 === o && (i += 1), o = 12 === o ? 1 : o + 1), n.checkVoid(i, o, a)[0] && n.addClass(t, d[1]), n.options.festival && n.festival(t, o + "." + a), t.setAttribute("y", i), t.setAttribute("m", o), t.setAttribute("d", a), i = o = a = null
        }), n.valid = !n.hasClass(s.thisDay, d[1]), n.ymd = s.ymd, d.year.value = n.ymd[0] + "年", d.month.value = n.digit(n.ymd[1] + 1) + "月", n.each(d.mms, function (e, t) {
            var a = n.checkVoid(n.ymd[0], (0 | t.getAttribute("m")) + 1);
            "y" === a[0] || "m" === a[0] ? n.addClass(t, d[1]) : n.removeClass(t, d[1]), n.removeClass(t, d[2]), a = null
        }), n.addClass(d.mms[n.ymd[1]], d[2]), s.times = [0 | n.inymd[3] || 0, 0 | n.inymd[4] || 0, 0 | n.inymd[5] || 0], n.each(new Array(3), function (e) {
            n.hmsin[e].value = n.digit(n.timeVoid(s.times[e], e) ? 0 | n.mins[e + 3] : 0 | s.times[e])
        }), n[n.valid ? "removeClass" : "addClass"](d.ok, d[1])
    }, n.festival = function (e, t) {
        var a;
        switch (t) {
            case"1.1":
                a = "元旦";
                break;
            case"3.8":
                a = "妇女";
                break;
            case"4.5":
                a = "清明";
                break;
            case"5.1":
                a = "劳动";
                break;
            case"6.1":
                a = "儿童";
                break;
            case"9.10":
                a = "教师";
                break;
            case"10.1":
                a = "国庆"
        }
        a && (e.innerHTML = a), a = null
    }, n.viewYears = function (e) {
        var t = n.query, a = "";
        n.each(new Array(14), function (t) {
            a += 7 === t ? "<li " + (parseInt(d.year.value) === e ? 'class="' + d[2] + '"' : "") + ' y="' + e + '">' + e + "年</li>" : '<li y="' + (e - 7 + t) + '">' + (e - 7 + t) + "年</li>"
        }), t("#laydate_ys").innerHTML = a, n.each(t("#laydate_ys li"), function (e, t) {
            "y" === n.checkVoid(t.getAttribute("y"))[0] ? n.addClass(t, d[1]) : n.on(t, "click", function (e) {
                n.stopmp(e).reshow(), n.viewDate(0 | this.getAttribute("y"), n.ymd[1], n.ymd[2])
            })
        })
    }, n.initDate = function () {
        var e = (n.query, new Date), t = n.elem[d.elemv].match(/\d+/g) || [];
        t.length < 3 && (t = n.options.start.match(/\d+/g) || [], t.length < 3 && (t = [e.getFullYear(), e.getMonth() + 1, e.getDate()])), n.inymd = t, n.viewDate(t[0], t[1] - 1, t[2])
    }, n.iswrite = function () {
        var e = n.query, t = {time: e("#laydate_hms")};
        n.shde(t.time, !n.options.istime), n.shde(d.oclear, !("isclear" in n.options ? n.options.isclear : 1)), n.shde(d.otoday, !("istoday" in n.options ? n.options.istoday : 1)), n.shde(d.ok, !("issure" in n.options ? n.options.issure : 1))
    }, n.orien = function (e, t) {
        var a, s = n.elem.getBoundingClientRect();
        e.style.left = s.left + (t ? 0 : n.scroll(1)) + "px", a = s.bottom + e.offsetHeight / 1.5 <= n.winarea() ? s.bottom - 1 : s.top > e.offsetHeight / 1.5 ? s.top - e.offsetHeight + 1 : n.winarea() - e.offsetHeight, e.style.top = Math.max(a + (t ? 0 : n.scroll()), 1) + "px"
    }, n.follow = function (e) {
        n.options.fixed ? (e.style.position = "fixed", n.orien(e, 1)) : (e.style.position = "absolute", n.orien(e))
    }, n.viewtb = function () {
        var e, t = [], a = ["日", "一", "二", "三", "四", "五", "六"], o = {}, d = s[i]("table"), r = s[i]("thead");
        return r.appendChild(s[i]("tr")), o.creath = function (e) {
            var t = s[i]("th");
            t.innerHTML = a[e], r[l]("tr")[0].appendChild(t), t = null
        }, n.each(new Array(6), function (a) {
            t.push([]), e = d.insertRow(0), n.each(new Array(7), function (n) {
                t[a][n] = 0, 0 === a && o.creath(n), e.insertCell(n)
            })
        }), d.insertBefore(r, d.children[0]), d.id = d.className = "laydate_table", e = t = null, d.outerHTML.toLowerCase()
    }(), n.view = function (e, t) {
        var o, l = n.query, r = {};
        t = t || e, n.elem = e, n.options = t, n.options.format || (n.options.format = a.format), n.options.start = n.options.start || "", n.mm = r.mm = [n.options.min || a.min, n.options.max || a.max], n.mins = r.mm[0].match(/\d+/g), n.maxs = r.mm[1].match(/\d+/g), n.box ? n.shde(n.box) : (o = s[i]("div"), o.id = d[0], o.className = d[0], o.style.cssText = "position: absolute;", o.setAttribute("name", "laydate-v" + laydate.v), o.innerHTML = r.html = '<div class="laydate_top"><div class="laydate_ym laydate_y" id="laydate_YY"><a class="laydate_choose laydate_chprev laydate_tab"><cite></cite></a><input id="laydate_y" readonly><label></label><a class="laydate_choose laydate_chnext laydate_tab"><cite></cite></a><div class="laydate_yms"><a class="laydate_tab laydate_chtop"><cite></cite></a><ul id="laydate_ys"></ul><a class="laydate_tab laydate_chdown"><cite></cite></a></div></div><div class="laydate_ym laydate_m" id="laydate_MM"><a class="laydate_choose laydate_chprev laydate_tab"><cite></cite></a><input id="laydate_m" readonly><label></label><a class="laydate_choose laydate_chnext laydate_tab"><cite></cite></a><div class="laydate_yms" id="laydate_ms">' + function () {
            var e = "";
            return n.each(new Array(12), function (t) {
                e += '<span m="' + t + '">' + n.digit(t + 1) + "月</span>"
            }), e
        }() + "</div></div></div>" + n.viewtb + '<div class="laydate_bottom"><ul id="laydate_hms"><li class="laydate_sj">时间</li><li><input readonly>:</li><li><input readonly>:</li><li><input readonly></li></ul><div class="laydate_time" id="laydate_time"></div><div class="laydate_btn"><a id="laydate_clear">清空</a><a id="laydate_today">今天</a><a id="laydate_ok">确认</a></div>' + (a.isv ? '<a href="http://sentsin.com/layui/laydate/" class="laydate_v" target="_blank">laydate-v' + laydate.v + "</a>" : "") + "</div>", s.body.appendChild(o), n.box = l("#" + d[0]), n.events(), o = null), n.follow(n.box), t.zIndex ? n.box.style.zIndex = t.zIndex : n.removeCssAttr(n.box, "z-index"), n.stopMosup("click", n.box), n.initDate(), n.iswrite(), n.check()
    }, n.reshow = function () {
        return n.each(n.query("#" + d[0] + " .laydate_show"), function (e, t) {
            n.removeClass(t, "laydate_show")
        }), this
    }, n.close = function () {
        n.reshow(), n.shde(n.query("#" + d[0]), 1), n.elem = null
    }, n.parse = function (e, t, s) {
        return e = e.concat(t), s = s || (n.options ? n.options.format : a.format), s.replace(/YYYY|MM|DD|hh|mm|ss/g, function (t, a) {
            return e.index = 0 | ++e.index, n.digit(e[e.index])
        })
    }, n.creation = function (e, t) {
        var a = (n.query, n.hmsin), s = n.parse(e, [a[0].value, a[1].value, a[2].value]);
        n.elem[d.elemv] = s, t || (n.close(), "function" == typeof n.options.choose && n.options.choose(s))
    }, n.events = function () {
        var e = n.query, a = {box: "#" + d[0]};
        n.addClass(s.body, "laydate_body"), d.tds = e("#laydate_table td"), d.mms = e("#laydate_ms span"), d.year = e("#laydate_y"), d.month = e("#laydate_m"), n.each(e(a.box + " .laydate_ym"), function (e, t) {
            n.on(t, "click", function (t) {
                n.stopmp(t).reshow(), n.addClass(this[l]("div")[0], "laydate_show"), e || (a.YY = parseInt(d.year.value), n.viewYears(a.YY))
            })
        }), n.on(e(a.box), "click", function () {
            n.reshow()
        }), a.tabYear = function (e) {
            0 === e ? n.ymd[0]-- : 1 === e ? n.ymd[0]++ : 2 === e ? a.YY -= 14 : a.YY += 14, e < 2 ? (n.viewDate(n.ymd[0], n.ymd[1], n.ymd[2]), n.reshow()) : n.viewYears(a.YY)
        }, n.each(e("#laydate_YY .laydate_tab"), function (e, t) {
            n.on(t, "click", function (t) {
                n.stopmp(t), a.tabYear(e)
            })
        }), a.tabMonth = function (e) {
            e ? (n.ymd[1]++, 12 === n.ymd[1] && (n.ymd[0]++, n.ymd[1] = 0)) : (n.ymd[1]--, n.ymd[1] === -1 && (n.ymd[0]--, n.ymd[1] = 11)), n.viewDate(n.ymd[0], n.ymd[1], n.ymd[2])
        }, n.each(e("#laydate_MM .laydate_tab"), function (e, t) {
            n.on(t, "click", function (t) {
                n.stopmp(t).reshow(), a.tabMonth(e)
            })
        }), n.each(e("#laydate_ms span"), function (e, t) {
            n.on(t, "click", function (e) {
                n.stopmp(e).reshow(), n.hasClass(this, d[1]) || n.viewDate(n.ymd[0], 0 | this.getAttribute("m"), n.ymd[2])
            })
        }), n.each(e("#laydate_table td"), function (e, t) {
            n.on(t, "click", function (e) {
                n.hasClass(this, d[1]) || (n.stopmp(e), n.creation([0 | this.getAttribute("y"), 0 | this.getAttribute("m"), 0 | this.getAttribute("d")]))
            })
        }), d.oclear = e("#laydate_clear"), n.on(d.oclear, "click", function () {
            n.elem[d.elemv] = "", n.close()
        }), d.otoday = e("#laydate_today"), n.on(d.otoday, "click", function () {
            var e = new Date;
            n.creation([e.getFullYear(), e.getMonth() + 1, e.getDate()])
        }), d.ok = e("#laydate_ok"), n.on(d.ok, "click", function () {
            n.valid && n.creation([n.ymd[0], n.ymd[1] + 1, n.ymd[2]])
        }), a.times = e("#laydate_time"), n.hmsin = a.hmsin = e("#laydate_hms input"), a.hmss = ["小时", "分钟", "秒数"], a.hmsarr = [], n.msg = function (t, s) {
            var i = '<div class="laydte_hsmtex">' + (s || "提示") + "<span>×</span></div>";
            "string" == typeof t ? (i += "<p>" + t + "</p>", n.shde(e("#" + d[0])), n.removeClass(a.times, "laydate_time1").addClass(a.times, "laydate_msg")) : (a.hmsarr[t] ? i = a.hmsarr[t] : (i += '<div id="laydate_hmsno" class="laydate_hmsno">', n.each(new Array(0 === t ? 24 : 60), function (e) {
                i += "<span>" + e + "</span>"
            }), i += "</div>", a.hmsarr[t] = i), n.removeClass(a.times, "laydate_msg"), n[0 === t ? "removeClass" : "addClass"](a.times, "laydate_time1")), n.addClass(a.times, "laydate_show"), a.times.innerHTML = i
        }, a.hmson = function (t, a) {
            var s = e("#laydate_hmsno span"), i = n.valid ? null : 1;
            n.each(s, function (e, s) {
                i ? n.addClass(s, d[1]) : n.timeVoid(e, a) ? n.addClass(s, d[1]) : n.on(s, "click", function (e) {
                    n.hasClass(this, d[1]) || (t.value = n.digit(0 | this.innerHTML))
                })
            }), n.addClass(s[0 | t.value], "laydate_click")
        }, n.each(a.hmsin, function (e, t) {
            n.on(t, "click", function (t) {
                n.stopmp(t).reshow(), n.msg(e, a.hmss[e]), a.hmson(this, e)
            })
        }), n.on(s, "mouseup", function () {
            var t = e("#" + d[0]);
            t && "none" !== t.style.display && (n.check() || n.close())
        }).on(s, "keydown", function (e) {
            e = e || t.event;
            var a = e.keyCode;
            13 === a && n.elem && n.creation([n.ymd[0], n.ymd[1] + 1, n.ymd[2]])
        })
    }, laydate.reset = function () {
        n.box && n.elem && n.follow(n.box)
    }, laydate.now = function (e, t) {
        var a = new Date(0 | e ? function (e) {
            return e < 864e5 ? +new Date + 864e5 * e : e
        }(parseInt(e)) : +new Date);
        return n.parse([a.getFullYear(), a.getMonth() + 1, a.getDate()], [a.getHours(), a.getMinutes(), a.getSeconds()], t)
    }, layui.addcss("modules/laydate/laydate.css", function () {
    }, "laydatecss"), e("laydate", laydate)
});