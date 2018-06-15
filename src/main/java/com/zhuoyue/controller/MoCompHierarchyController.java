package com.zhuoyue.controller;


import com.zhuoyue.bean.MoCompHierarchy;
import com.zhuoyue.common.dto.JsonResult;
import com.zhuoyue.common.web.BaseController;
import com.zhuoyue.service.MoCompHierarchyService;
import com.zhuoyue.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 14258
 */
@Api(value = "API - MoCompHierarchyController", description = "专业-楼层-树 接口详情")
@RestController
@RequestMapping("/mchierarchy/")
public class MoCompHierarchyController extends BaseController {

    @Resource
    private MoCompHierarchyService moCompHierarchyService;

    /**
     * 通过模型主键（modeId）获得（MoCompHierarchy）列表
     *
     * @param modeId
     * @return
     */
    public JsonResult getMoCompHierarchyListByModeId(String modeId) {
        List<MoCompHierarchy> moCompHierarchyList = moCompHierarchyService.getMoCompHierarchyListByModeId(modeId);
        return ok("成功", moCompHierarchyList);
    }


    /**
     * 通过模型主键（modeId）获得（专业-楼层）列表
     *
     * @param modeId
     * @return
     */
    @ApiOperation(value = "通过模型主键（modeId）获得（专业-楼层）列表", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "modeId", value = "模型id", required = true, dataType = "String", paramType = "query", defaultValue = "A981DA7159A44C2EA56A18DC525A601C"),
    })
    @GetMapping(value = "/getSpecialtyTreeByModeId")
    public JsonResult getSpecialtyTreeByModeId(@RequestParam String modeId) {
        ModelTree modelTree = moCompHierarchyService.getSpecialtyTreeByModeId(modeId);
        return ok("成功", modelTree);
    }

    @ApiOperation(value = "获取专业树", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "modeId", value = "模型id", required = true, dataType = "String", paramType = "query", defaultValue = "A981DA7159A44C2EA56A18DC525A601C"),
    })
    @GetMapping(value = "/getSpecialtyModelTree")
    public JsonResult getSpecialtyModelTree(@RequestParam String modeId) {
        ModelTree modelTree = moCompHierarchyService.getSpecialtyTreeByModeId(modeId);
        List<SpecialtyFloors> specialtyFloorsList = (List<SpecialtyFloors>) modelTree.getTree();

        List<Tree> treeList = new ArrayList<>();
        for (SpecialtyFloors sf : specialtyFloorsList) {
            Tree tree = new Tree();
            tree.setName(sf.getSpecialty());
            tree.setType(Tree.SPECIALTY);
            List<Floor> floorList = sf.getFloors();
            if (CollectionUtils.isNotEmpty(floorList)) {
                List<Tree> treeList1 = new ArrayList<>();
                for (Floor floor : floorList) {
                    Tree tree1 = new Tree();
                    tree1.setName(floor.getFloor());
                    tree1.setType(Tree.FLOOR);
                    List<Category> categoryList = floor.getCategories();
                    //类型
                    if (CollectionUtils.isNotEmpty(categoryList)) {
                        List<Tree> treeList2 = new ArrayList<>();
                        for (Category category : categoryList) {
                            Tree tree2 = new Tree();
                            tree2.setName(category.getCategoryName());
                            tree2.setType(Tree.CATEGORY);
                            List<Family> familyList = category.getFamilies();
                            //族
                            if (CollectionUtils.isNotEmpty(familyList)) {
                                List<Tree> treeList3 = new ArrayList<>();
                                for (Family family : familyList) {
                                    Tree tree3 = new Tree();
                                    tree3.setName(family.getFamily());
                                    tree3.setType(Tree.FAMILYTYPE);
                                    List<FamilyType> familyTypeList = family.getFamilyTypes();
                                    //族类型
                                    if (CollectionUtils.isNotEmpty(familyTypeList)) {
                                        List<Tree> treeList4 = new ArrayList<>();
                                        for (FamilyType familyType : familyTypeList) {
                                            Tree tree4 = new Tree();
                                            tree4.setId(familyType.getId());
                                            tree4.setName(familyType.getName());
                                            tree4.setType(Tree.FAMILYTYPE);
                                            treeList4.add(tree4);
                                        }
                                        if (CollectionUtils.isNotEmpty(treeList4)) {
                                            tree3.setChildren(treeList4);

                                        }
                                    }

                                    treeList3.add(tree3);
                                }
                                if (CollectionUtils.isNotEmpty(treeList3)) {
                                    tree2.setChildren(treeList3);
                                }
                            }
                            treeList2.add(tree2);
                        }
                        if (CollectionUtils.isNotEmpty(treeList2)) {
                            tree1.setChildren(treeList2);
                        }
                    }
                    treeList1.add(tree1);

                }
                if (CollectionUtils.isNotEmpty(treeList1)) {
                    tree.setChildren(treeList1);
                }
            }
            treeList.add(tree);
        }


        return ok("成功", treeList);
    }


    /**
     * 通过模型主键（modeId）获得（楼层-专业）列表
     *
     * @param modeId
     * @return
     */
    @ApiOperation(value = "通过模型主键（modeId）获得（楼层-专业）列表", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "modeId", value = "模型id", required = true, dataType = "String", paramType = "query", defaultValue = "A981DA7159A44C2EA56A18DC525A601C"),
    })
    @GetMapping(value = "/getFloorsTreeByModeId")
    public JsonResult getFloorsTreeByModeId(String modeId) {
        ModelTree modelTree = moCompHierarchyService.getFloorsTreeByModeId(modeId);
        return ok("成功", modelTree);
    }

    /**
     * 通过模型 （modeId）获得（楼层-专业）列表 IOS 测试
     *
     * @param modeId
     * @return
     */
    @ApiOperation(value = "通过模型 （modeId）获得（楼层-专业）列表 IOS 测试", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "modeId", value = "模型id", required = true, dataType = "String", paramType = "query", defaultValue = "A981DA7159A44C2EA56A18DC525A601C"),
    })
    @GetMapping(value = "/getTypesByModeIdIOS")
    public JsonResult getFloorsAndSpecialsByModeId(String modeId) {
        ModelTree modelTree = moCompHierarchyService.getFloorsAndSpecialsByModeId(modeId);
        return ok("成功", modelTree);
    }
}
