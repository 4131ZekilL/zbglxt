import request from '@/utils/request'
export default {
  getIndexData() {
    return request({
      url: `/jewelleryservice/index/index`,
      method: 'get'
    })
  }
}