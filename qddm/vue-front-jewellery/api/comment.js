import request from '@/utils/request'

export default {
    getPageList(page, limit, jewelleryId) {
      return request({
        url: `/jewelleryservice/comment/${page}/${limit}`,
        method: 'get',
        params: {jewelleryId}
      })
    },
    addComment(comment) {
      return request({
        url: `/jewelleryservice/comment/addComment`,
        method: 'post',
        data: comment
      })
    }
  }