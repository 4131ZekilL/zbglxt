import request from '@/utils/request'

export default {

  getClassofyList() {
    return request({
      url: `/jewelleryservice/classofy/getAllClassofy`,
      method: 'get'
    })
  }
}