// ==UserScript==
// @name         Save Order Image
// @namespace    http://192.168.0.64
// @version      0.1
// @description  Save Order Image
// @author       You
// @match        http://192.168.0.64/order/viewOrder

// @require      http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js
// @require      https://sweetalert.js.org/assets/sweetalert/sweetalert.min.js

// @grant        unsafeWindow
// @grant        GM_addStyle
// ==/UserScript==

(function() {
    'use strict';
    GM_addStyle("https://cdn.bootcss.com/jquery-confirm/3.2.3/jquery-confirm.min.css");

    unsafeWindow.downloadIamge = function(imgsrc, name) {//下载图片地址和图片名
        let image = new Image();
        // 解决跨域 Canvas 污染问题
        image.setAttribute("crossOrigin", "anonymous");
        image.onload = function() {
            let canvas = document.createElement("canvas");
            canvas.width = image.width;
            canvas.height = image.height;
            let context = canvas.getContext("2d");
            context.drawImage(image, 0, 0, image.width, image.height);
            let url = canvas.toDataURL("image/png"); //得到图片的base64编码数据
            let a = document.createElement("a"); // 生成一个a元素
            let event = new MouseEvent("click"); // 创建一个单击事件
            a.download = name || "photo"; // 设置图片名称
            a.href = url; // 将生成的URL设置为a.href属性
            a.dispatchEvent(event); // 触发a的单击事件
        };
        image.src = imgsrc;
    }

    //operate data
    unsafeWindow.operateData = function () {
        console.log("Begin to operate datas!");
        var trs = document.getElementById("order_grid_table").getElementsBySelector("tr.even");
        var aside = $(".grid");
        for(var i = 0; i< trs.length; i++){
            var tr = trs[i];
            var orderId = tr.getElementsByTagName("td")[2].getElementsBySelector("div.columnCell")[2].getElementsByTagName("span")[1].innerText;
            var imageUrl = tr.getElementsByTagName("td")[3].getElementsBySelector("img")[1].getAttribute("src");

            var type = imageUrl.split(".")[1];

            //var aImage = '<a id="'+ orderId +'" stype="display: block" herf="http://192.168.0.64' + imageUrl + '" download="'+ orderId + '.'+ type +'">'+ orderId +'</a>';

            //var dom = document.createElement("a");
            //dom.href = document.getElementById('myCanvas').toDataURL("http://192.168.0.64" + imageUrl);
            //dom.download =  orderId + '.'+ type;
            //dom.click();

            downloadIamge("http://192.168.0.64" + imageUrl, orderId + '.'+ type);

        }

    }

    var butHtml = '<input id="saveImage" style="width: 100%; height: 40px; margin: 20px 0; background-color: green;" onclick="operateData()" type="button" value="丁给佩保存图片"></input>';
    var canvas = '<canvas id="myCanvas" style="display: none">';
    var aside = $(".grid");
    aside.append(canvas);
    aside.append(butHtml);
})();