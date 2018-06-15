function manage() {
    this.init = function () {
        this.zTreeObj;
        this.setting = {

            showLine: true,                  //是否显示节点间的连线
            checkable: true,                  //每个节点上是否显示 CheckBox

            check: {
                enable: true,
                chkStyle: "checkbox",
                chkboxType: {"Y": "ps", "N": "ps"}
            },
            view: {
                selectedMulti: false,
                showIcon: true,
                fontCss: {color: "#ffffff"}//字体颜色
            },
            callback: {
                onClick: zTreeOnClick
            }

        };
        this.param = "modeId=" + "A981DA7159A44C2EA56A18DC525A601C";
        this.getTreeData();
        this.tree();
    };
    //获得树型数据
    this.getTreeData = function () {
        var _this = this;

        call_service("/mchierarchy/getSpecialtyModelTree", false, "get", _this.param,
            function (data) {
                console.log(data)
                _this.zTreeNodes = data;
            }, function (message) {
                msgTips("错误")
            });
    };
    //构建zTree
    this.tree = function () {
        var _this = this;
        // _this.getTreeData();
        _this.zTreeObj = $.fn.zTree.init($("#tree"), this.setting, this.zTreeNodes);
    };

}

var my_index_manager = new manage();

function zTreeOnClick(event, treeId, treeNode) {
    console.log(treeNode.tId + ", " + treeNode.name + "   " + treeNode.type + "  " + treeNode.id);
    if (4 == treeNode.type) {
        onLoader('/mcfamilyType/downLoadObj?modeId=A981DA7159A44C2EA56A18DC525A601C&familyTypeId=' + treeNode.id);
    }

};
$(function () {
    my_index_manager.init();
});

