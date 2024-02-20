import request from '@/utils/request'

export default {
//添加目录
  addVideo(video) {
    return request({
      url: `/jewelleryservice/video/addVideo/`,
      method: 'post',
      data:video
    })
  },
  //删除目录
  deleteVideo(id) {
    return request({
      url: `/jewelleryservice/video/`+id,
      method: 'delete'
    })
  },
   //删除sp
   deleteAliyunVod(id) {
    return request({
      url: `/jewelleryvod/video/removeVideo/`+id,
      method: 'delete'
    })
  },
}