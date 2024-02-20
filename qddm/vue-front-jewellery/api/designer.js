import request from '@/utils/request'
export default {

  getPageList(page, limit) {
    return request({
      url: `/jewelleryservice/designerfront/pageList/${page}/${limit}`,
      method: 'get'
    })
  },
  getById(designerid) {
    return request({
      url: `/jewelleryservice/designer/getDesigner/${designerid}`,
      method: 'get'
    })
  },
}