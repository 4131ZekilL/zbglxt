import request from '@/utils/request'

export default {
//添加珠宝信息
  addJewelleryInfo(jewelleryInfo) {
    return request({
      url: `/jewelleryservice/jewellery/addJewelleryInfo`,
      method: 'post',
      data:jewelleryInfo
    })
  },
  //查询所有设计师
  getListDesigner(){
    return request({
      url: `/jewelleryservice/designer/findAll`,
      method: 'get'
    })
  },
  //根据id查询珠宝
  getJewelleryInfo(id){
    return request({
      url: `/jewelleryservice/jewellery/getJewelleryInfo/`+id,
      method: 'get'
    })
  },
  //修改珠宝信息
  updateJewelleryInfo(jewelleryInfo) {
    return request({
      url: `/jewelleryservice/jewellery/updateJewelleryInfo`,
      method: 'post',
      data:jewelleryInfo
    })
  },
  //根据id显示提交信息
  getPublishInfo(id) {
    return request({
      url: `/jewelleryservice/jewellery/getPublishJewelleryInfo/`+id,
      method: 'get'
    })
  },
  //更新珠宝状态
  updateJewelleryStatus(id) {
    return request({
      url: `/jewelleryservice/jewellery/updateJewelleryStatus/`+id,
      method: 'post'
    })
  },
  //查询珠宝信息
  getList(page,limit,searchObj){
    return request({
      url: `/jewelleryservice/jewellery/pageListJewellery/${page}/${limit}`,
      method: 'post',
      data: searchObj
    })
  },
  //删除珠宝
  deleteJewelleryId(id){
    return request({
        url: `/jewelleryservice/jewellery/${id}`,
        method: 'delete'
    })
},

}