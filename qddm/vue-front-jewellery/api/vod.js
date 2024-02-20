import request from '@/utils/request'

export default {

  getPlayAuth(vid) {
    return request({
      url: `/jewelleryvod/video/getPlayAuth/${vid}`,
      method: 'get'
    })
  }

}