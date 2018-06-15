package com.zhuoyue.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhuoyue.bean.MoCompHierarchy;
import com.zhuoyue.common.log.annotation.ServiceLog;
import com.zhuoyue.dao.MoCompHierarchyMapper;
import com.zhuoyue.service.MoCompHierarchyService;
import com.zhuoyue.service.MoFamilyTypeService;
import com.zhuoyue.vo.Category;
import com.zhuoyue.vo.Family;
import com.zhuoyue.vo.Floor;
import com.zhuoyue.vo.FloorSpecialties;
import com.zhuoyue.vo.ModelTree;
import com.zhuoyue.vo.Specialty;
import com.zhuoyue.vo.SpecialtyFloors;

/**
 * @author 14258
 */
@Service
@Transactional
public class MoCompHierarchyServiceImpl implements MoCompHierarchyService {

    @Resource
    private MoCompHierarchyMapper moCompHierarchyDao;

    @Resource
    private MoFamilyTypeService moFamilyTypeService;


    /**
     * 通过模型主键（modeId）获得（MoCompHierarchy）列表
     *
     * @param modeId
     * @return
     */
    @Override
    public List<MoCompHierarchy> getMoCompHierarchyListByModeId(String modeId) {
        List<MoCompHierarchy> moCompHierarchyList = moCompHierarchyDao.getMoCompHierarchyListByModeId(modeId);
        return moCompHierarchyList;
    }

    /**
     * 通过模型主键（modeId）获得（专业-楼层）列表
     *
     * @param modeId
     * @return
     */
    @Override
    public ModelTree getSpecialtyTreeByModeId(String modeId) {

        List<MoCompHierarchy> hierarchyList = this.getMoCompHierarchyListByModeId(modeId);

        List<SpecialtyFloors> specialtyFloorsList = new ArrayList<>();

//        1.遍历集合，把专业提取出来，放入set
        Set<String> specialtySet = new HashSet();
        for (MoCompHierarchy moc : hierarchyList) {
            specialtySet.add(moc.getSpecialty());
        }

//        2.遍历set，依次取出专业
        for (String specialty : specialtySet) {

            SpecialtyFloors specialtyFloors = new SpecialtyFloors();
            specialtyFloors.setSpecialty(specialty);
            //相同专业的集合 -装饰专业
            List<MoCompHierarchy> hierarchyList1 = new ArrayList<>();
            for (MoCompHierarchy hierarchy : hierarchyList) {
                if (specialty.equals(hierarchy.getSpecialty())) {
                    hierarchyList1.add(hierarchy);
                }
            }

            //2.遍历set，依次取出楼层
            Set<String> floorSet = new HashSet();
            for (MoCompHierarchy moc : hierarchyList1) {
                floorSet.add(moc.getFloor());
            }

            List<Floor> floorList = new ArrayList<>();
            for (String floor : floorSet) {
                Floor floor1 = new Floor();
                floor1.setFloor(floor);
                List<Category> categoryList = new ArrayList<>();
                List<MoCompHierarchy> hierarchyList2 = new ArrayList<>();
                for (MoCompHierarchy moc : hierarchyList1) {
                    if (floor.equals(moc.getFloor())) {
                        Category category = new Category();
                        category.setCategoryName(moc.getCategory());
                        category.setCategoryId(moc.getId());
                        String categoryId = moc.getId();
                        List<Family> familyList = moFamilyTypeService.getFamilyListByCategoryId(categoryId, modeId);
                        category.setFamilies(familyList);
                        categoryList.add(category);
                        hierarchyList2.add(moc);

                    }
                }
                floor1.setCategories(categoryList);
                floorList.add(floor1);
            }
            specialtyFloors.setFloors(floorList);
            specialtyFloorsList.add(specialtyFloors);

        }

        ModelTree modelTree = new ModelTree();
        modelTree.setTree(specialtyFloorsList);
        modelTree.setTreeType("1");
        modelTree.setModelId(modeId);
        return modelTree;
    }

    /**
     * 通过模型主键（modeId）获得（楼层-专业）列表
     *
     * @param modeId
     * @return
     */
    @Override
    @ServiceLog(description = "请求参数:modeId")
    public ModelTree getFloorsTreeByModeId(String modeId) {


        List<MoCompHierarchy> hierarchyList = this.getMoCompHierarchyListByModeId(modeId);

        List<FloorSpecialties> floorSpecialtiesList = new ArrayList<>();

//        1.遍历集合，把楼层提取出来，放入set
        Set<String> floorSet = new HashSet();
        for (MoCompHierarchy moc : hierarchyList) {
            floorSet.add(moc.getFloor());
        }


        for (String floor : floorSet) {
            //
            List<MoCompHierarchy> hierarchyList1 = new ArrayList<>();
            for (MoCompHierarchy hierarchy : hierarchyList) {
                if (floor.equals(hierarchy.getFloor())) {
                    hierarchyList1.add(hierarchy);
                }
            }


            Set<String> specialtySet = new HashSet();
            for (MoCompHierarchy moc : hierarchyList1) {
                specialtySet.add(moc.getSpecialty());
            }


            FloorSpecialties floorSpecialties = new FloorSpecialties();
            floorSpecialties.setFloor(floor);
            List<Specialty> specialtyList = new ArrayList<>();
            for (String specialty : specialtySet) {
                Specialty specialty1 = new Specialty();
                specialty1.setSpecialty(specialty);
                List<Category> categoryList = new ArrayList<>();

                List<MoCompHierarchy> hierarchyList2 = new ArrayList<>();
                for (MoCompHierarchy moc : hierarchyList1) {
                    if (specialty.equals(moc.getSpecialty())) {
                        Category category = new Category();
                        category.setCategoryId(moc.getId());
                        category.setCategoryName(moc.getCategory());
                        categoryList.add(category);
                        hierarchyList2.add(moc);
                    }
                }
                specialty1.setCategories(categoryList);
                specialtyList.add(specialty1);
            }
            floorSpecialties.setSpecialties(specialtyList);
            floorSpecialtiesList.add(floorSpecialties);
        }


        ModelTree modelTree = new ModelTree();
        modelTree.setTree(floorSpecialtiesList);
        modelTree.setTreeType("2");
        modelTree.setModelId(modeId);

        return modelTree;

    }

	@Override
	public ModelTree getFloorsAndSpecialsByModeId(String modeId) {
		 
		List<MoCompHierarchy> hierarchyList = moCompHierarchyDao.getMoCompHierarchyListByModeIdIOS(modeId);

		// 1.遍历集合，把楼层提取出来，放入set
		Set<String> floorSet = new HashSet();
		for (MoCompHierarchy moc : hierarchyList) {
			floorSet.add(moc.getFloor());
		}
		
		Map<String,List> floorsMap = new HashMap<>();
		// 2.遍历set，依次取出专业
		for (String floor : floorSet) {
			List floorList = new ArrayList<>();
			for (MoCompHierarchy moc : hierarchyList) {
				if(moc.getFloor().equals(floor)){
					Map<String,String> floorMap = new HashMap();
					floorMap.put("specialty", moc.getSpecialty());
					floorMap.put("id", moc.getId());
					floorList.add(floorMap);
				}
			}
			floorsMap.put(floor,floorList);
		}
		
		ModelTree modelTree = new ModelTree();
		modelTree.setTree(floorsMap);
		modelTree.setTreeType("2");
		modelTree.setModelId(modeId);

		return modelTree;
	}


}