import request from '@/utils/request'
export default {
  getPageList(page, limit, searchObj) {
    return request({
      url: `/jewelleryservice/jewelleryfront/pageList/${page}/${limit}`,
      method: 'post',
      data: searchObj
    })
  },

  getNestedTreeList2() {
    return request({
      url: `/jewelleryservice/classofy/getAllClassofy`,
      method: 'get'
    })
  },
  getById(jewelleryId) {
    return request({
        url: `/jewelleryservice/jewelleryfront/getFrontJewelleryInfo/${jewelleryId}`,
        method: 'get'
    })
}
}