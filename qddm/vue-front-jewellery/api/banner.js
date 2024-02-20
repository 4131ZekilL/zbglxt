import request from '@/utils/request'
export default {
  getListBanner() {
    return request({
      url: `/jewellerycms/banneruser/getAllBanner`,
      method: 'get'
    })
  }
}