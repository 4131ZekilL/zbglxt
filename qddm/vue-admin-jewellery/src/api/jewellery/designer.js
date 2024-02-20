import request from '@/utils/request'

export default{

    //1 设计师列表（条件查询分页）
    //current:当前页  limit:每页记录数  designerQuery:条件对象
    getDesignerListPage(current,limit,designerQuery){
        return request({
            url: `/jewelleryservice/designer/pageDesignerCondition/${current}/${limit}`,
            method: 'post',
            //designerQuery条件对象，后端使用RequestBody获取数据
            //data表示把对象转换json进行传递到接口里面
            data:designerQuery
          })
    },
    //删除设计师
    deleteDesignerId(id){
        return request({
            url: `/jewelleryservice/designer/${id}`,
            method: 'delete'
        })
    },
    //添加设计师
    addDesigner(designer) {
        return request({
            url:`/jewelleryservice/designer/addDesigner`,
            method: 'post',
            data: designer
        })
    },
    //根据id查询设计师
    getDesignerInfo(id){
        return request({
            url:`/jewelleryservice/designer/getDesigner/${id}`,
            method: 'get'
        })
    },
    //修改设计师
    updateDesignerInfo(designer){
        return request({
            url:`/jewelleryservice/designer/updateDesigner`,
            method: 'post',
            data: designer
        })
    }
}
