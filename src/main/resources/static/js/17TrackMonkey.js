// ==UserScript==
// @name         Get 17Track Data
// @namespace    it.tomtop.com
// @version      0.1
// @description  Get 17Track data!

// @match        https://t.17track.net/*
// @grant        GM_xmlhttpRequest
// @grant        GM_addStyle
// @grant        GM_download
// @grant        GM_log
// @grant        unsafeWindow

// @require      http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js
// @require      https://sweetalert.js.org/assets/sweetalert/sweetalert.min.js

// @connect      192.168.0.219
// ==/UserScript==

(function () {
    'use strict';

    GM_addStyle("https://cdn.bootcss.com/jquery-confirm/3.2.3/jquery-confirm.min.css");

    try {
        //删除广告
        var guanggao = $("#DA_V6-T-Right1");
        guanggao.remove();

        //operate data
        unsafeWindow.operateData = function () {
            console.log("Begin to operate datas!");
            var tracklist = $("div.tracklist-tracking");

            if (tracklist == null || tracklist.length == 0) {
                alert("There are no tracks!")
                return;
            }

            var tracks = [];
            for (var i = 0; i < tracklist.length; i++) {
                var trackElement = tracklist[i];

                //单号
                var logisticsCode = trackElement.getElementsByClassName("text-uppercase")[0].innerText;

                var from = trackElement.getElementsByClassName("from")[0].getElementsByTagName("i")[0].innerText;
                var toElement = trackElement.getElementsByClassName("to")[0];
                var to = "";
                if(toElement.getElementsByTagName("i").length > 0){
                    to = toElement.getElementsByTagName("i")[0].innerText;
                }

                var trackDetailsElelment = trackElement.getElementsByTagName("dd");
                var trackDetails = [];
                for (var j = 0; j < trackDetailsElelment.length; j++) {
                    //时间
                    var time = trackDetailsElelment[j].getElementsByTagName("time")[0].innerText;
                    //快递信息
                    var detail = trackDetailsElelment[j].getElementsByTagName("p")[0].innerText;

                    var trackDetail = {
                        "dateTime": time + ":00",
                        "detail": detail
                    }
                    trackDetails.push(trackDetail);
                }

                //封装数据
                var track = {
                    "code": logisticsCode,
                    "keyFrom": from,
                    "keyTo": to,
                    "details": trackDetails
                }
                tracks.push(track);
            }

            console.log(tracks);

            //保存数据
            var exception = function (resp) {
                $("#zhiziBnt").css("background-color", "red");
                $("#zhiziBnt").css("border-color", "red");
                swal.stopLoading();
                console.log("Save failed! message is: " + JSON.stringify(resp));
                swal({
                    icon: "error",
                    title: "Save failed! Please view the details in the console!",
                });
            };

            swal("加载中，请等待...", {
                buttons: false,
                closeOnEsc: false,
                closeOnClickOutside: false,
            });

            GM_xmlhttpRequest({
                method: "post",
                url: "http://192.168.0.219/ets/monkey/seventeen/track",
                data: JSON.stringify(tracks),
                headers: {
                    "Content-Type": 'application/json; charset=UTF-8',
                    "accept": 'application/json, text/javascript, */*; q=0.01',
                },
                dataType: "json",
                onload: function (res) {
                    swal.stopLoading();
                    if(!res.responseText){
                        swal({
                            icon: "error",
                            title: "Undefind Error!",
                        });
                    }
                    //请求数据
                    var result = JSON.parse(res.responseText);
                    if (result.code == 200) {
                        $("#zhiziBnt").css("background-color", "green");
                        $("#zhiziBnt").css("border-color", "green");
                        swal({
                            icon: "success",
                        });
                        return;
                    }else if(result.code == 400){
                        $("#zhiziBnt").css("background-color", "red");
                        $("#zhiziBnt").css("border-color", "red");
                        swal({
                            icon: "error",
                            title: "Data is null!",
                        });
                        return;
                    }

                    $("#zhiziBnt").css("background-color", "red");
                    $("#zhiziBnt").css("border-color", "red");
                    console.log("Save failed! Error code is: "+ result.code +" message is: " + result.msg);
                    swal({
                        icon: "warning",
                        title: "Partial failure! Please view the details in the console!",
                    });
                },
                onabort: exception,
                onerror: exception,
                ontimeout: exception
            });


        }

        //添加按钮
        var butHtml = '<button id="zhiziBnt" style="margin-top: 20px; background-color: green; border-color: green;" type="button" onclick="operateData()" class="btn btn-block btn-lg btn-warning waves-effect" title="Set Result To ZhiZi"> <span>Send Result To ZhiZi</span></button>';
        var aside = $("div.page-content>aside");
        aside.append(butHtml);
    } catch (e) {
        $("#zhiziBnt").css("background-color", "red");
        $("#zhiziBnt").css("border-color", "red");
        console.log("Error: " + JSON.stringify(e));
        swal({
            icon: "error",
            title: "Init Tampermonkey failed! Please view the details in the console!",
        });
    }

})();