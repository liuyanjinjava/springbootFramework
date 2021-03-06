package ${daoPackagePath};

import ${entityPackagePath}.${className};

import java.util.List;

/**
* ${classDesc}Dao层
*
* @author ${authorName}
* Created on ${createDate}.
*/
public interface ${className}Dao {

/**
* 新增${classDesc}
* @param ${className?lower_case} ${classDesc}
*
* @return ${classDesc}
*/
void add(${className} ${className?lower_case});

/**
* 删除${classDesc}
*
* @param id 要删除的${classDesc}主键
* @return ${classDesc}
*/
void delete(String id);


/**
* 修改${classDesc}
*
* @param ${className?lower_case} 要修改的${classDesc}
* @return ${classDesc}
*/
void update(${className} ${className?lower_case});

/**
* 查询${classDesc}列表
*
* @return ${classDesc}列表
*/
List<${className}> list();

/**
* 获取${classDesc}详情
*
* @param id 要查询的${classDesc}主键
* @return ${classDesc}
*/
${className} byId(String id);
}
