var controls;    //控制器
var mouse;    // 鼠标
var raycaster;   //射线器
var objectsList = [];   //元素
var selectedHex;        //选中的颜色
var selecteObjName;     //选中的名称
var modeId = "A981DA7159A44C2EA56A18DC525A601C";  //模型 ID

var loadObjUrl;
var globalPlane;

var normal = new THREE.Vector3(1, 0, 0);
var constant = 0;
var clipPlanes = new THREE.Plane(normal, constant);
var Empty = Object.freeze([]);


//容器div ，宽度 ，高度
var container;
//树div
var treeDiv;
//相机，场景，加载，灯光,
var camera, scene, renderer, light, stats;
//加载器
var loader;

function init() {
    //容器div ，宽度 ，高度
    container = $("#container");

    treeDiv = $("#treeDiv")

    //camera 设置相机（视野，显示口的宽高比，近裁剪面，远裁剪面）
    camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 1, 4000);
    //设置相机的视点
    camera.position.set(200, 100, 150);
    //设置相机的朝向
    // camera.lookAt(new THREE.Vector3(0,0,0));

    // scene   创建场景
    scene = new THREE.Scene();
    //0x050505   #7B7B7B
    scene.background = new THREE.Color('#7B7B7B');


    //light  灯光     //添加环境光
    var ambientLight = new THREE.AmbientLight(0x404040);
    scene.add(ambientLight);
    //创建一个平行光光源照射到物体上
    var directionalLight1 = new THREE.DirectionalLight(0xC0C090);
    //设置平型光照射方向，
    directionalLight1.position.set(-100, -50, 100);
    //将灯光放到场景当中
    scene.add(directionalLight1);

    var directionalLight2 = new THREE.DirectionalLight(0xC0C090);
    directionalLight2.position.set(100, 50, -100);
    scene.add(directionalLight2);


    // texture
    var manager = new THREE.LoadingManager();
    manager.onProgress = function (item, loaded, total) {
        console.log(item, loaded, total);
    };

    // model
    loader = new THREE.OBJLoader(manager);     //obj加载器


    // renderer 建立渲染器，这里Three.js提供了多种渲染器，WebGLRenderer等
    renderer = new THREE.WebGLRenderer({
        antialias: true
    });
    //设置设备像素比
    renderer.setPixelRatio(window.devicePixelRatio);
    // 设置渲染器的高度和宽度，如果加上第三个值 false，则按场景大小显示，等比例缩放
    renderer.setSize(window.innerWidth, window.innerHeight, false);
    //设置背景
    renderer.setClearColor(0x000000, 1.0);
    //将渲染器添加到html当中
    container.append(renderer.domElement);

    //事件
    renderer.domElement.addEventListener('mousedown', onMouseDown, false);// 鼠标按下后触发

    window.addEventListener('resize', onWindowResize, false);//浏览器大小改变


    // controls, 用户交互插件 鼠标左键按住旋转，右键按住平移，滚轮缩放
    controls = new THREE.OrbitControls(camera, renderer.domElement);
    // 如果使用animate方法时，将此函数删除
    //controls.addEventListener( 'change', render );
    // 使动画循环使用时阻尼或自转 意思是否有惯性
    controls.enableDamping = true;
    //动态阻尼系数 就是鼠标拖拽旋转灵敏度
    //controls.dampingFactor = 0.25;
    //是否可以缩放
    controls.enableZoom = true;
    //是否自动旋转
    controls.autoRotate = false;
    //设置相机距离原点的最近距离，下边比上面数值大，不然模型不能放大缩小
    controls.minDistance = 10;
    //设置相机距离原点的最远距离
    controls.maxDistance = 2000;
    //是否开启右键拖拽
    controls.enablePan = true;
    controls.target.set(0, 12, 0);
    camera.position.set(2, 18, 28);
    controls.update();

    raycaster = new THREE.Raycaster();
    mouse = new THREE.Vector2();


    var helpers = new THREE.Group();
    helpers.add(new THREE.PlaneHelper(clipPlanes, 900, 0x00ffff));
    helpers.visible = false;
    scene.add(helpers);


    // GUI
    var gui = new dat.GUI();
    // gui.domElement.style = 'position:absolute;top:10px;right:200px;height:500px;';//控制右上角位置
    //剖切控制条参数
    var params = {
        clipIntersection: false,
        planeConstant: 0,
        showHelpers: false
    };

    //剖切的内容
    gui.add(params, 'clipIntersection').name('剖切').onChange(function (value) {
        if (value) {
            renderer.clippingPlanes = [clipPlanes];
            renderer.localClippingEnabled = true;
        } else {

            renderer.clippingPlanes = Empty;
            renderer.localClippingEnabled = false;
        }

    });
    gui.add(params, 'planeConstant', -1000, 1000).step(1).name('滚动条').onChange(function (value) {
        clipPlanes.constant = value;
        //	render();
    });
    gui.add(params, 'showHelpers').name('显示剖切面板').onChange(function (value) {
        helpers.visible = value;
    });


    stats = new Stats();
    container.append(stats.dom);
    // stats.domElement.style='position:absolute';
    // gui.domElement.style =;

    animate();
}


function reBuildTheOjbect() {
    objectsList.forEach(function (obj, index) {
        if (undefined != selecteObjName && selecteObjName == obj.name) {
            obj.material.color.set(selectedHex);
        }
    });
}

//
function animate() {
    //更新控制器
    controls.update();
    render();

    //更新性能插件
    stats.update();
    requestAnimationFrame(animate);


}

//
function render() {
    renderer.render(scene, camera);
}


function onLoader(url) {
    loader.load(url, function (object) {
        //给对象添加材质
        var index = url.indexOf("familyTypeId");
        var familyTypeId = url.substring(index);
        object.name = familyTypeId;
        var childrens = object.children;
        for (var i = 0, l = childrens.length; i < l; i++) {
            //var material=new THREE.MeshLambertMaterial({color:'#ee8888'});//材质对象     绿色材质0x80ee10
            //childrens[i].material = material;
            // 使用定义兰伯特网孔材质  （MeshLambertMaterial）进行着色器，这里只绘制了一个红色
            var material = new THREE.MeshLambertMaterial({
                color: '#ee8888',
                clippingPlanes: [clipPlanes],
                clipShadows: true
            });
            //var material=new THREE.MeshLambertMaterial({color:'#ee8888'});//材质对象
            childrens[i].material = material;

            objectsList.push(childrens[i]);
            //scene.add(childrens[i]);
        }

        scene.add(object);

    }, onProgress, onError);


}

var onProgress = function (xhr) {
    if (xhr.lengthComputable) {
        var percentComplete = xhr.loaded / xhr.total * 1000;
        console.log(Math.round(percentComplete, 2) + '% downloaded');
    }
};
var onError = function (xhr) {
    alert("数据加载错误 ...请检查现有的网络。。");
    console.error(xhr);

};

//窗口变动触发的函数
function onWindowResize() {
    camera.aspect = window.innerWidth / window.innerHeight;
    camera.updateProjectionMatrix();
    renderer.setSize(window.innerWidth, window.innerHeight);
}

//鼠标按下事件大小改变
function onMouseDown(event) {
    event.preventDefault();

    if (0 == event.button) {
        mouse.x = ( event.clientX / renderer.domElement.clientWidth) * 2 - 1;
        mouse.y = -(event.clientY / renderer.domElement.clientHeight) * 2 + 1;
        // console.log(mouse.x +" "+ mouse.y);
        raycaster.setFromCamera(mouse, camera);
        var intersects = raycaster.intersectObjects(objectsList);

        reBuildTheOjbect();
        for (var i = 0; i < intersects.length; i++) {
            selectedHex = intersects[i].object.material.color.getHex();
            selecteObjName = intersects[i].object.name;
            //16777215
            intersects[i].object.material.color.set(0x1A75FF);
            var paramParentName = intersects[i].object.parent.name;
            console.log(paramParentName);

            var param = {
                "modeId": modeId,
                "familyTypeId": paramParentName.substring(13),
                "elementName": selecteObjName
            }
            //  console.log(param);
            $("#elementContainer").html("");
            call_service("/mcfamilyType/getElementByIdAndName", false, "get", param,
                function (data) {
                    //  console.log(data);
                    var innerHtml = "属性信息为: ";
                    $.each(data.properties, function (index, content) {
                        console.log(content.group);
                        innerHtml += content.group + "-->";
                        $.each(content.items, function (index, content) {
                            innerHtml += content.key + " :" + content.value;
                        });

                    });
                    $("#elementContainer").html(innerHtml);

                }, function (message) {
                    msgTips("错误")
                });
            break;
        }
    }
}